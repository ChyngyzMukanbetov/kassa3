package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.AdditionalPaymentDocDao;
import com.example.kassa3.model.document.AdditionalPaymentDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.document.AdditionalCreditDocService;
import com.example.kassa3.service.abstracts.document.AdditionalPaymentDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AdditionalPaymentDocServiceImpl extends ReadWriteServiceImpl<AdditionalPaymentDoc, Long> implements AdditionalPaymentDocService {
    private final AdditionalPaymentDocDao additionalPaymentDocDao;
    private final AdditionalCreditDocService additionalCreditDocService;
    private final BalanceService balanceService;

    public AdditionalPaymentDocServiceImpl(AdditionalPaymentDocDao additionalPaymentDocDao, AdditionalCreditDocService additionalCreditDocService, BalanceService balanceService) {
        super(additionalPaymentDocDao);
        this.additionalPaymentDocDao = additionalPaymentDocDao;
        this.additionalCreditDocService = additionalCreditDocService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public AdditionalPaymentDoc persist(AdditionalPaymentDoc additionalPaymentDoc) {

        if (!additionalPaymentDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1309", "Попытка сохранить(persist) неактивный(activate = false) additionalPaymentDoc в Service");
        }

        Balance balance = balanceService.findByShop(additionalPaymentDoc.getShop());

        if (additionalPaymentDoc.getAdditionalCreditDoc() != null) {
            BigDecimal sumOfPaymentsInPaymentDocList = BigDecimal.ZERO;
            for (AdditionalPaymentDoc previousAdditionalPaymentDoc : additionalPaymentDoc.getAdditionalCreditDoc().getAdditionalPaymentDocList()) {
                if (additionalPaymentDoc.isActivate()) {
                    sumOfPaymentsInPaymentDocList = sumOfPaymentsInPaymentDocList.add(previousAdditionalPaymentDoc.getSum());
                }
            }
            if (additionalPaymentDoc.getAdditionalCreditDoc().getSumOfCredit().subtract(sumOfPaymentsInPaymentDocList).subtract(additionalPaymentDoc.getSum()).compareTo(BigDecimal.ZERO) == 0 ) {
                additionalPaymentDoc.getAdditionalCreditDoc().setReturned(true);
                additionalPaymentDoc.getAdditionalCreditDoc().setReturnFactDate(additionalPaymentDoc.getPaymentDate());
                additionalCreditDocService.update(additionalPaymentDoc.getAdditionalCreditDoc());
            }
            balance.setCreditSum(balance.getCreditSum().subtract(additionalPaymentDoc.getSum()));
        }

        if (additionalPaymentDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().subtract(additionalPaymentDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().subtract(additionalPaymentDoc.getSum()));
        }
        balanceService.update(balance);
        additionalPaymentDocDao.persist(additionalPaymentDoc);
        return additionalPaymentDoc;
    }

    @Transactional
    public AdditionalPaymentDoc deactivate(AdditionalPaymentDoc additionalPaymentDoc) {
        if (!additionalPaymentDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1605", "AdditionalPaymentDoc уже является деактивированным: additionalPaymentDocID:" + additionalPaymentDoc.getId());
        }
        Balance balance = balanceService.findByShop(additionalPaymentDoc.getShop());

        if (additionalPaymentDoc.getAdditionalCreditDoc() != null) {
            BigDecimal sumOfPaymentsInPaymentDocList = BigDecimal.ZERO;
            for (AdditionalPaymentDoc previousAdditionalPaymentDoc : additionalPaymentDoc.getAdditionalCreditDoc().getAdditionalPaymentDocList()) {
                if (additionalPaymentDoc.isActivate()) {
                    sumOfPaymentsInPaymentDocList = sumOfPaymentsInPaymentDocList.add(previousAdditionalPaymentDoc.getSum());
                }
            }
            if (additionalPaymentDoc.getAdditionalCreditDoc().getSumOfCredit().subtract(sumOfPaymentsInPaymentDocList).add(additionalPaymentDoc.getSum()).compareTo(BigDecimal.ZERO) != 0 ) {
                additionalPaymentDoc.getAdditionalCreditDoc().setReturned(false);
                additionalPaymentDoc.getAdditionalCreditDoc().setReturnFactDate(null);
                additionalCreditDocService.update(additionalPaymentDoc.getAdditionalCreditDoc());
            }
            if (!additionalPaymentDoc.getAdditionalCreditDoc().isWrittenOff()) {
                balance.setCreditSum(balance.getCreditSum().add(additionalPaymentDoc.getSum()));
            }
        }

        if (additionalPaymentDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().add(additionalPaymentDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().add(additionalPaymentDoc.getSum()));
        }
        balanceService.update(balance);
        additionalPaymentDoc.setActivate(false);
        additionalPaymentDoc.setDeactivateDate(LocalDate.now());
        additionalPaymentDocDao.update(additionalPaymentDoc);
        return additionalPaymentDoc;
    }
}