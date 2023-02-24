package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.AdditionalIncomeDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalIncomeDocCreateDto;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.AdditionalDebitDocService;
import com.example.kassa3.service.abstracts.document.AdditionalIncomeDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class AdditionalIncomeDocValidation {

    private final AdditionalDebitDocService additionalDebitDocService;
    private final AdditionalIncomeDocService additionalIncomeDocService;
    private final UserService userService;
    private final ShopService shopService;
    private final AdditionalIncomeDocCreateConverter additionalIncomeDocCreateConverter;

    @Transactional
    public AdditionalIncomeDoc validateAndPersist(AdditionalIncomeDocCreateDto additionalIncomeDocCreateDto, Principal principal) {

        if (!userService.existsById(additionalIncomeDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1601", "User с таким id отсутствует, userId:" + additionalIncomeDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(additionalIncomeDocCreateDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1602", "Вы указали не Ваш userId в поле additionalIncomeDocCreateDto.userId");
//        }

        if (!shopService.existsById(additionalIncomeDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1603", "Shop с таким id отсутствует: shopId:" + additionalIncomeDocCreateDto.getShopId());
        }

//        if (!shopService.findById(additionalIncomeDocCreateDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1604", "Shop с таким id отсутствует:" + additionalIncomeDocCreateDto.getShopId());
//        }

        if (additionalIncomeDocCreateDto.getAdditionalDebitDocId() != null) {
            if (!additionalDebitDocService.existsById(additionalIncomeDocCreateDto.getAdditionalDebitDocId())) {
                throw new ItemAddSellWriteOffException("1605", "AdditionalDebitDoc с таким id отсутствует: AdditionalDebitDocId:" + additionalIncomeDocCreateDto.getAdditionalDebitDocId());
            } else {
                if (!additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getUser().getId().equals(additionalIncomeDocCreateDto.getUserId())) {
                    throw new ItemAddSellWriteOffException("1606", "userId в additionalIncomeDoc документа и userId в AdditionalDebitDoc должны быть идентичны");
                }
                if (!additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getShop().getId().equals(additionalIncomeDocCreateDto.getShopId())) {
                    throw new ItemAddSellWriteOffException("1607", "shopId в additionalIncomeDoc документа и shopId в AdditionalDebitDoc должны быть идентичны");
                }
                if (additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).isReturned()) {
                    throw new ItemAddSellWriteOffException("1608", "Данный долг(DebitDoc) уже помечен как оплаченный, дата фактического возврата: " + additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getReturnFactDate());
                }
                if (additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getDebitDate().isAfter(additionalIncomeDocCreateDto.getIncomeDate())) {
                    throw new ItemAddSellWriteOffException("1609", "Дата оплаты(incomeDate) не должна быть раньше Даты долга(debitDate) в DebitDoc, DebitDoc.debitDate: " + additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getDebitDate());
                }
                if (additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getSumOfDebit().compareTo(additionalIncomeDocCreateDto.getSum()) < 0) {
                    throw new ItemAddSellWriteOffException("1610", "Сумма платежа(Sum) не должна быть больше суммы долга(SumOfDebit) в DebitDoc, DebitDoc.SumOfDebit: " + additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getSumOfDebit());
                }


                if (additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getAdditionalIncomeDocList() != null) {
                    BigDecimal sumOfIncomesInAdditionalIncomeDocList = BigDecimal.ZERO;
                    for (AdditionalIncomeDoc additionalIncomeDoc : additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getAdditionalIncomeDocList()) {
                        if (additionalIncomeDoc.isActivate()) {
                            sumOfIncomesInAdditionalIncomeDocList = sumOfIncomesInAdditionalIncomeDocList.add(additionalIncomeDoc.getSum());
                        }
                    }
                    if (additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getSumOfDebit().subtract(sumOfIncomesInAdditionalIncomeDocList).compareTo(additionalIncomeDocCreateDto.getSum()) < 0) {
                        throw new ItemAddSellWriteOffException("1611", "Сумма платежей(Sum) по всем debitIncomeDoc не должна быть больше суммы долга(SumOfDebit) в DebitDoc, сумма оплат по ранее уплаченным документам: "
                                + sumOfIncomesInAdditionalIncomeDocList + ", DebitDoc.SumOfDebit: " + additionalDebitDocService.findById(additionalIncomeDocCreateDto.getAdditionalDebitDocId()).getSumOfDebit());
                    }
                }
            }
        }
        return additionalIncomeDocService.persist(additionalIncomeDocCreateConverter.toModel(additionalIncomeDocCreateDto));
    }

    @Transactional
    public AdditionalIncomeDoc validateAndDeactivate(Long additionalIncomeDocId, Principal principal) {
        if (additionalIncomeDocId == null) {
            throw new ItemAddSellWriteOffException("1612", "AdditionalIncomeDocId не должно быть null");
        }
        if (!additionalIncomeDocService.existsById(additionalIncomeDocId)) {
            throw new ItemAddSellWriteOffException("1613", "AdditionalIncomeDoc с таким id отсутствует: additionalIncomeDocID:" + additionalIncomeDocId);
        }

        AdditionalIncomeDoc additionalIncomeDoc = additionalIncomeDocService.findById(additionalIncomeDocId);

        if (!additionalIncomeDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1614", "AdditionalIncomeDoc уже является деактивированным: additionalIncomeDocID:" + additionalIncomeDocId);
        }
//        if (!userService.findByUsername(principal.getName()).equals(additionalIncomeDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("1615", "AdditionalIncomeDoc с таким id отсутствует: additionalIncomeDocID:" + additionalIncomeDoc.getId());
//        }
        return additionalIncomeDocService.deactivate(additionalIncomeDoc);
    }

    @Transactional
    public AdditionalIncomeDoc validateAndChangeOldToNew(Long additionalIncomeDocId, AdditionalIncomeDocCreateDto additionalIncomeDocCreateDto, Principal principal) {
        validateAndDeactivate(additionalIncomeDocId, principal);
        return validateAndPersist(additionalIncomeDocCreateDto, principal);
    }
}
