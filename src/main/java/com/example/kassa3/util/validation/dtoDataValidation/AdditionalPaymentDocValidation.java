package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.AdditionalPaymentDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.document.AdditionalPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalIncomeDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalPaymentDocCreateDto;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.AdditionalCreditDocService;
import com.example.kassa3.service.abstracts.document.AdditionalPaymentDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class AdditionalPaymentDocValidation {
    private final AdditionalCreditDocService additionalCreditDocService;
    private final AdditionalPaymentDocService additionalPaymentDocService;
    private final AdditionalPaymentDocCreateConverter additionalPaymentDocCreateConverter;
    private final UserService userService;
    private final ShopService shopService;

    @Transactional
    public AdditionalPaymentDoc validateAndPersist(AdditionalPaymentDocCreateDto additionalPaymentDocCreateDto, Principal principal) {

        if (!userService.existsById(additionalPaymentDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1701", "User с таким id отсутствует, userId:" + additionalPaymentDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(additionalPaymentDocCreateDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1702", "Вы указали не Ваш userId в поле additionalPaymentDocCreateDto.userId");
//        }

        if (!shopService.existsById(additionalPaymentDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1703", "Shop с таким id отсутствует: shopId:" + additionalPaymentDocCreateDto.getShopId());
        }

//        if (!shopService.findById(additionalPaymentDocCreateDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1704", "Shop с таким id отсутствует:" + additionalPaymentDocCreateDto.getShopId());
//        }

        if (additionalPaymentDocCreateDto.getAdditionalCreditDocId() != null) {
            if (!additionalCreditDocService.existsById(additionalPaymentDocCreateDto.getAdditionalCreditDocId())) {
                throw new ItemAddSellWriteOffException("1705", "AdditionalCreditDoc с таким id отсутствует: AdditionalCreditDocId:" + additionalPaymentDocCreateDto.getAdditionalCreditDocId());
            } else {
                if (!additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getUser().getId().equals(additionalPaymentDocCreateDto.getUserId())) {
                    throw new ItemAddSellWriteOffException("1706", "userId в additionalPaymentDoc документа и userId в AdditionalCreditDoc должны быть идентичны");
                }
                if (!additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getShop().getId().equals(additionalPaymentDocCreateDto.getShopId())) {
                    throw new ItemAddSellWriteOffException("1707", "shopId в additionalPaymentDoc документа и shopId в AdditionalCreditDoc должны быть идентичны");
                }
                if (additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).isReturned()) {
                    throw new ItemAddSellWriteOffException("1708", "Данный долг(CreditDoc) уже помечен как оплаченный, дата фактического возврата: " + additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getReturnFactDate());
                }
                if (additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getCreditDate().isAfter(additionalPaymentDocCreateDto.getPaymentDate())) {
                    throw new ItemAddSellWriteOffException("1709", "Дата оплаты(paymentDate) не должна быть раньше Даты долга(creditDate) в CreditDoc, CreditDoc.creditDate: " + additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getCreditDate());
                }
                if (additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getSumOfCredit().compareTo(additionalPaymentDocCreateDto.getSum()) < 0) {
                    throw new ItemAddSellWriteOffException("1710", "Сумма платежа(Sum) не должна быть больше суммы долга(SumOfCredit) в CreditDoc, CreditDoc.SumOfCredit: " + additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getSumOfCredit());
                }


                if (additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getAdditionalPaymentDocList() != null) {
                    BigDecimal sumOfPaymentsInAdditionalPaymentDocList = BigDecimal.ZERO;
                    for (AdditionalPaymentDoc additionalPaymentDoc : additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getAdditionalPaymentDocList()) {
                        if (additionalPaymentDoc.isActivate()) {
                            sumOfPaymentsInAdditionalPaymentDocList = sumOfPaymentsInAdditionalPaymentDocList.add(additionalPaymentDoc.getSum());
                        }
                    }
                    if (additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getSumOfCredit().subtract(sumOfPaymentsInAdditionalPaymentDocList).compareTo(additionalPaymentDocCreateDto.getSum()) < 0) {
                        throw new ItemAddSellWriteOffException("1711", "Сумма платежей(Sum) по всем creditPaymentDoc не должна быть больше суммы долга(SumOfCredit) в CreditDoc, сумма оплат по ранее уплаченным документам: "
                                + sumOfPaymentsInAdditionalPaymentDocList + ", CreditDoc.SumOfCredit: " + additionalCreditDocService.findById(additionalPaymentDocCreateDto.getAdditionalCreditDocId()).getSumOfCredit());
                    }
                }
            }
        }
        AdditionalPaymentDoc additionalPaymentDoc = additionalPaymentDocCreateConverter.toModel(additionalPaymentDocCreateDto);
        additionalPaymentDocService.persist(additionalPaymentDoc);
        return additionalPaymentDoc;
    }

    @Transactional
    public AdditionalPaymentDoc validateAndDeactivate(Long additionalPaymentDocId, Principal principal) {
        if (additionalPaymentDocId == null) {
            throw new ItemAddSellWriteOffException("1712", "AdditionalPaymentDocId не должно быть null");
        }
        if (!additionalPaymentDocService.existsById(additionalPaymentDocId)) {
            throw new ItemAddSellWriteOffException("1713", "AdditionalPaymentDoc с таким id отсутствует: additionalPaymentDocID:" + additionalPaymentDocId);
        }

        AdditionalPaymentDoc additionalPaymentDoc = additionalPaymentDocService.findById(additionalPaymentDocId);

        if (!additionalPaymentDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1714", "AdditionalPaymentDoc уже является деактивированным: additionalPaymentDocID:" + additionalPaymentDoc.getId());
        }
//        if (!userService.findByUsername(principal.getName()).equals(additionalPaymentDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("1715", "AdditionalPaymentDoc с таким id отсутствует: additionalPaymentDocID:" + additionalPaymentDoc.getId());
//        }
        return additionalPaymentDocService.deactivate(additionalPaymentDoc);
    }

    @Transactional
    public AdditionalPaymentDoc validateAndChangeOldToNew(Long additionalPaymentDocId, AdditionalPaymentDocCreateDto additionalPaymentDocCreateDto, Principal principal) {
        validateAndDeactivate(additionalPaymentDocId, principal);
        return validateAndPersist(additionalPaymentDocCreateDto, principal);
    }
}
