package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.CreditPaymentDocDao;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.document.CreditDocService;
import com.example.kassa3.service.abstracts.document.CreditPaymentDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CreditPaymentDocServiceImpl extends ReadWriteServiceImpl<CreditPaymentDoc, Long> implements CreditPaymentDocService {

    private final CreditPaymentDocDao creditPaymentDocDao;
    private final CreditDocService creditDocService;
    private final BalanceService balanceService;

    public CreditPaymentDocServiceImpl(CreditPaymentDocDao creditPaymentDocDao, CreditDocService creditDocService, BalanceService balanceService) {
        super(creditPaymentDocDao);
        this.creditPaymentDocDao = creditPaymentDocDao;
        this.creditDocService = creditDocService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public CreditPaymentDoc persist(CreditPaymentDoc creditPaymentDoc) {

        if (!creditPaymentDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1301", "Попытка сохранить(persist) неактивный(activate = false) creditPaymentDoc в Service");
        }

        Balance balance = balanceService.findByShop(creditPaymentDoc.getShop());

        creditPaymentDoc.setPartner(creditPaymentDoc.getCreditDoc().getPartner());

        BigDecimal sumOfPaymentsInPaymentDocList = BigDecimal.ZERO;
        for (CreditPaymentDoc previousCreditPaymentDoc : creditPaymentDoc.getCreditDoc().getCreditPaymentDocList()) {
            if (previousCreditPaymentDoc.isActivate()) {
                sumOfPaymentsInPaymentDocList = sumOfPaymentsInPaymentDocList.add(previousCreditPaymentDoc.getSum());
            }
        }
        if (creditPaymentDoc.getCreditDoc().getSumOfCredit().subtract(sumOfPaymentsInPaymentDocList).subtract(creditPaymentDoc.getSum()).compareTo(BigDecimal.ZERO) == 0 ) {
            creditPaymentDoc.getCreditDoc().setReturned(true);
            creditPaymentDoc.getCreditDoc().setReturnFactDate(creditPaymentDoc.getPaymentDate());
            creditDocService.update(creditPaymentDoc.getCreditDoc());
        }
        balance.setCreditSum(balance.getCreditSum().subtract(creditPaymentDoc.getSum()));
        if (creditPaymentDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().subtract(creditPaymentDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().subtract(creditPaymentDoc.getSum()));
        }
        balanceService.update(balance);
        creditPaymentDocDao.persist(creditPaymentDoc);
        return creditPaymentDoc;
    }

    @Transactional
    public CreditPaymentDoc deactivate(CreditPaymentDoc creditPaymentDoc) {
        if (!creditPaymentDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1605", "CreditPaymentDoc уже является деактивированным: creditPaymentDocID:" + creditPaymentDoc.getId());
        }
        Balance balance = balanceService.findByShop(creditPaymentDoc.getShop());

        if (creditPaymentDoc.getCreditDoc() != null) {
            BigDecimal sumOfPaymentsInPaymentDocList = BigDecimal.ZERO;
            for (CreditPaymentDoc previousCreditPaymentDoc : creditPaymentDoc.getCreditDoc().getCreditPaymentDocList()) {
                if (creditPaymentDoc.isActivate()) {
                    sumOfPaymentsInPaymentDocList = sumOfPaymentsInPaymentDocList.add(previousCreditPaymentDoc.getSum());
                }
            }
            if (creditPaymentDoc.getCreditDoc().getSumOfCredit().subtract(sumOfPaymentsInPaymentDocList).add(creditPaymentDoc.getSum()).compareTo(BigDecimal.ZERO) != 0 ) {
                creditPaymentDoc.getCreditDoc().setReturned(false);
                creditPaymentDoc.getCreditDoc().setReturnFactDate(null);
                creditDocService.update(creditPaymentDoc.getCreditDoc());
            }
            if (!creditPaymentDoc.getCreditDoc().isWrittenOff()) {
                balance.setCreditSum(balance.getCreditSum().add(creditPaymentDoc.getSum()));
            }
        }

        if (creditPaymentDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().add(creditPaymentDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().add(creditPaymentDoc.getSum()));
        }
        balanceService.update(balance);
        creditPaymentDoc.setActivate(false);
        creditPaymentDoc.setDeactivateDate(LocalDate.now());
        creditPaymentDocDao.update(creditPaymentDoc);
        return creditPaymentDoc;
    }
}
