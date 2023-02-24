package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.DebitIncomeDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditPaymentDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitIncomeDocCreateDto;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.DebitDocService;
import com.example.kassa3.service.abstracts.document.DebitIncomeDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class DebitIncomeDocValidation {

    private final DebitDocService debitDocService;
    private final DebitIncomeDocService debitIncomeDocService;
    private final DebitIncomeDocCreateConverter debitIncomeDocCreateConverter;
    private final UserService userService;
    private final ShopService shopService;

    @Transactional
    public DebitIncomeDoc validateAndPersist(DebitIncomeDocCreateDto debitIncomeDocCreateDto, Principal principal) {

        if (!userService.existsById(debitIncomeDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1201", "User с таким id отсутствует, userId:" + debitIncomeDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(debitIncomeDocCreateDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1202", "Вы указали не Ваш userId в поле debitIncomeDocCreateDto.userId");
//        }

        if (!shopService.existsById(debitIncomeDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1203", "Shop с таким id отсутствует: shopId:" + debitIncomeDocCreateDto.getShopId());
        }

//        if (!shopService.findById(debitIncomeDocCreateDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1204", "Shop с таким id отсутствует:" + debitIncomeDocCreateDto.getShopId());
//        }

        if (!debitDocService.existsById(debitIncomeDocCreateDto.getDebitDocId())) {
            throw new ItemAddSellWriteOffException("1205", "DebitDoc с таким id отсутствует: DebitDocId:" + debitIncomeDocCreateDto.getDebitDocId());
        } else {
            if (!debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getUser().getId().equals(debitIncomeDocCreateDto.getUserId())) {
                throw new ItemAddSellWriteOffException("1206", "userId в debitIncomeDoc документа и userId в DebitDoc должны быть идентичны");
            }
            if (!debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getShop().getId().equals(debitIncomeDocCreateDto.getShopId())) {
                throw new ItemAddSellWriteOffException("1207", "shopId в debitIncomeDoc документа и shopId в DebitDoc должны быть идентичны");
            }
            if (debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).isReturned()) {
                throw new ItemAddSellWriteOffException("1208", "Данный долг(DebitDoc) уже помечен как оплаченный, дата фактического возврата: " + debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getReturnFactDate());
            }
            if (debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getDebitDate().isAfter(debitIncomeDocCreateDto.getIncomeDate())) {
                throw new ItemAddSellWriteOffException("1209", "Дата оплаты(incomeDate) не должна быть раньше Даты долга(debitDate) в DebitDoc, DebitDoc.debitDate: " + debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getDebitDate());
            }
            if (debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getSumOfDebit().compareTo(debitIncomeDocCreateDto.getSum()) < 0) {
                throw new ItemAddSellWriteOffException("1210", "Сумма платежа(Sum) не должна быть больше суммы долга(SumOfDebit) в DebitDoc, DebitDoc.SumOfDebit: " + debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getSumOfDebit());
            }


            if (debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getDebitIncomeDocList() != null) {
                BigDecimal sumOfIncomesInIncomeDocList = BigDecimal.ZERO;
                for (DebitIncomeDoc debitIncomeDoc : debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getDebitIncomeDocList()) {
                    if (debitIncomeDoc.isActivate()) {
                        sumOfIncomesInIncomeDocList = sumOfIncomesInIncomeDocList.add(debitIncomeDoc.getSum());
                    }
                }
                if (debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getSumOfDebit().subtract(sumOfIncomesInIncomeDocList).compareTo(debitIncomeDocCreateDto.getSum()) < 0) {
                    throw new ItemAddSellWriteOffException("1211", "Сумма платежей(Sum) по всем debitIncomeDoc не должна быть больше суммы долга(SumOfDebit) в DebitDoc, сумма оплат по ранее уплаченным документам: "
                            + sumOfIncomesInIncomeDocList + ", DebitDoc.SumOfDebit: " + debitDocService.findById(debitIncomeDocCreateDto.getDebitDocId()).getSumOfDebit());
                }
            }
        }
        DebitIncomeDoc debitIncomeDoc = debitIncomeDocCreateConverter.toModel(debitIncomeDocCreateDto);
        debitIncomeDocService.persist(debitIncomeDoc);
        return debitIncomeDoc;
    }

    @Transactional
    public DebitIncomeDoc validateAndDeactivate(Long debitIncomeDocId, Principal principal) {
        if (debitIncomeDocId == null) {
            throw new ItemAddSellWriteOffException("1212", "DebitIncomeDocId не должно быть null");
        }
        if (!debitIncomeDocService.existsById(debitIncomeDocId)) {
            throw new ItemAddSellWriteOffException("1213", "DebitIncomeDoc с таким id отсутствует: debitIncomeDocID:" + debitIncomeDocId);
        }

        DebitIncomeDoc debitIncomeDoc = debitIncomeDocService.findById(debitIncomeDocId);

        if (!debitIncomeDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1214", "DebitIncomeDoc уже является деактивированным: debitIncomeDocID:" + debitIncomeDoc.getId());
        }
//        if (!userService.findByUsername(principal.getName()).equals(debitIncomeDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("1215", "DebitIncomeDoc с таким id отсутствует: debitIncomeDocID:" + debitIncomeDoc.getId());
//        }
        return debitIncomeDocService.deactivate(debitIncomeDoc);
    }

    @Transactional
    public DebitIncomeDoc validateAndChangeOldToNew(Long debitIncomeDocId, DebitIncomeDocCreateDto debitIncomeDocCreateDto, Principal principal) {
        validateAndDeactivate(debitIncomeDocId, principal);
        return validateAndPersist(debitIncomeDocCreateDto, principal);
    }
}
