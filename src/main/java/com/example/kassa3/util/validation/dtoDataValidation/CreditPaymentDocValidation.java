package com.example.kassa3.util.validation.dtoDataValidation;


import com.example.kassa3.converter.documentConverter.documentCreateConverter.CreditPaymentDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalPaymentDoc;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalPaymentDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditPaymentDocCreateDto;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.CreditDocService;
import com.example.kassa3.service.abstracts.document.CreditPaymentDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class CreditPaymentDocValidation {

    private final CreditDocService creditDocService;
    private final CreditPaymentDocService creditPaymentDocService;
    private final CreditPaymentDocCreateConverter creditPaymentDocCreateConverter;
    private final UserService userService;
    private final ShopService shopService;

    @Transactional
    public CreditPaymentDoc validateAndPersist(CreditPaymentDocCreateDto creditPaymentDocCreateDto, Principal principal) {

        if (!userService.existsById(creditPaymentDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1101", "User с таким id отсутствует, userId:" + creditPaymentDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemArrivalDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1102", "Вы указали не Ваш userId в поле itemArrivalDocDto.userId");
//        }

        if (!shopService.existsById(creditPaymentDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1103", "Shop с таким id отсутствует: shopId:" + creditPaymentDocCreateDto.getShopId());
        }

//        if (!shopService.findById(itemArrivalDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1104", "Shop с таким id отсутствует:" + itemArrivalDocDto.getShopId());
//        }

        if (!creditDocService.existsById(creditPaymentDocCreateDto.getCreditDocId())) {
            throw new ItemAddSellWriteOffException("1105", "CreditDoc с таким id отсутствует: CreditDocId:" + creditPaymentDocCreateDto.getCreditDocId());
        } else {
            if (!creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getUser().getId().equals(creditPaymentDocCreateDto.getUserId())) {
                throw new ItemAddSellWriteOffException("1106", "userId в creditPaymentDoc документа и userId в CreditDoc должны быть идентичны");
            }
            if (!creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getShop().getId().equals(creditPaymentDocCreateDto.getShopId())) {
                throw new ItemAddSellWriteOffException("1107", "shopId в creditPaymentDoc документа и shopId в CreditDoc должны быть идентичны");
            }
            if (creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).isReturned()) {
                throw new ItemAddSellWriteOffException("1108", "Данный долг(CreditDoc) уже помечен как оплаченный, дата фактического возврата: " + creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getReturnFactDate());
            }
            if (creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getCreditDate().isAfter(creditPaymentDocCreateDto.getPaymentDate())) {
                throw new ItemAddSellWriteOffException("1109", "Дата оплаты(paymentDate) не должна быть раньше Даты кредита(creditDate) в CreditDoc, CreditDoc.creditDate: " + creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getCreditDate());
            }
            if (creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getSumOfCredit().compareTo(creditPaymentDocCreateDto.getSum()) < 0) {
                throw new ItemAddSellWriteOffException("1110", "Сумма платежа(Sum) не должна быть больше суммы долга(SumOfCredit) в CreditDoc, CreditDoc.SumOfCredit: " + creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getSumOfCredit());
            }


            if (creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getCreditPaymentDocList() != null) {
                BigDecimal sumOfPaymentsInPaymentDocList = BigDecimal.ZERO;
                for (CreditPaymentDoc creditPaymentDoc : creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getCreditPaymentDocList()) {
                    if (creditPaymentDoc.isActivate()) {
                        sumOfPaymentsInPaymentDocList = sumOfPaymentsInPaymentDocList.add(creditPaymentDoc.getSum());
                    }
                }
                if (creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getSumOfCredit().subtract(sumOfPaymentsInPaymentDocList).compareTo(creditPaymentDocCreateDto.getSum()) < 0) {
                    throw new ItemAddSellWriteOffException("1111", "Сумма платежей(Sum) по всем creditPaymentDoc не должна быть больше суммы долга(SumOfCredit) в CreditDoc, сумма оплат по ранее уплаченным документам: "
                            + sumOfPaymentsInPaymentDocList + ", CreditDoc.SumOfCredit: " + creditDocService.findById(creditPaymentDocCreateDto.getCreditDocId()).getSumOfCredit());
                }
            }
        }
        CreditPaymentDoc creditPaymentDoc = creditPaymentDocCreateConverter.toModel(creditPaymentDocCreateDto);
        creditPaymentDocService.persist(creditPaymentDoc);
        return creditPaymentDoc;
    }

    @Transactional
    public CreditPaymentDoc validateAndDeactivate(Long creditPaymentDocId, Principal principal) {
        if (creditPaymentDocId == null) {
            throw new ItemAddSellWriteOffException("1112", "CreditPaymentDocId не должно быть null");
        }
        if (!creditPaymentDocService.existsById(creditPaymentDocId)) {
            throw new ItemAddSellWriteOffException("1113", "CreditPaymentDoc с таким id отсутствует: creditPaymentDocID:" + creditPaymentDocId);
        }

        CreditPaymentDoc creditPaymentDoc = creditPaymentDocService.findById(creditPaymentDocId);

        if (!creditPaymentDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1114", "CreditPaymentDoc уже является деактивированным: creditPaymentDocID:" + creditPaymentDoc.getId());
        }
//        if (!userService.findByUsername(principal.getName()).equals(creditPaymentDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("1115", "CreditPaymentDoc с таким id отсутствует: creditPaymentDocID:" + creditPaymentDoc.getId());
//        }
        return creditPaymentDocService.deactivate(creditPaymentDoc);
    }

    @Transactional
    public CreditPaymentDoc validateAndChangeOldToNew(Long creditPaymentDocId, CreditPaymentDocCreateDto creditPaymentDocCreateDto, Principal principal) {
        validateAndDeactivate(creditPaymentDocId, principal);
        return validateAndPersist(creditPaymentDocCreateDto, principal);
    }
}
