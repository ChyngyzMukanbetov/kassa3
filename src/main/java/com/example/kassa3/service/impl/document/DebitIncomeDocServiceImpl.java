package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.DebitIncomeDocDao;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.document.DebitDocService;
import com.example.kassa3.service.abstracts.document.DebitIncomeDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DebitIncomeDocServiceImpl extends ReadWriteServiceImpl<DebitIncomeDoc, Long> implements DebitIncomeDocService {

    private final DebitIncomeDocDao debitIncomeDocDao;
    private final DebitDocService debitDocService;
    private final BalanceService balanceService;

    public DebitIncomeDocServiceImpl(DebitIncomeDocDao debitIncomeDocDao, DebitDocService debitDocService, BalanceService balanceService) {
        super(debitIncomeDocDao);
        this.debitIncomeDocDao = debitIncomeDocDao;
        this.debitDocService = debitDocService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public DebitIncomeDoc persist(DebitIncomeDoc debitIncomeDoc) {

        if (!debitIncomeDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1302", "Попытка сохранить(persist) неактивный(activate = false) debitIncomeDoc в Service");
        }

        Balance balance = balanceService.findByShop(debitIncomeDoc.getShop());

        debitIncomeDoc.setPartner(debitIncomeDoc.getDebitDoc().getPartner());

        BigDecimal sumOfIncomesInIncomeDocList = BigDecimal.ZERO;
        for (DebitIncomeDoc previousDebitIncomeDoc : debitIncomeDoc.getDebitDoc().getDebitIncomeDocList()) {
            if (debitIncomeDoc.isActivate()) {
                sumOfIncomesInIncomeDocList = sumOfIncomesInIncomeDocList.add(previousDebitIncomeDoc.getSum());
            }
        }
        if (debitIncomeDoc.getDebitDoc().getSumOfDebit().subtract(sumOfIncomesInIncomeDocList).subtract(debitIncomeDoc.getSum()).compareTo(BigDecimal.ZERO) == 0 ) {
            debitIncomeDoc.getDebitDoc().setReturned(true);
            debitIncomeDoc.getDebitDoc().setReturnFactDate(debitIncomeDoc.getIncomeDate());
            debitDocService.update(debitIncomeDoc.getDebitDoc());
        }
        balance.setDebitSum(balance.getDebitSum().subtract(debitIncomeDoc.getSum()));
        if (debitIncomeDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().add(debitIncomeDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().add(debitIncomeDoc.getSum()));
        }
        balanceService.update(balance);
        debitIncomeDocDao.persist(debitIncomeDoc);
        return debitIncomeDoc;
    }

    @Transactional
    public DebitIncomeDoc deactivate(DebitIncomeDoc debitIncomeDoc) {
        if (!debitIncomeDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1605", "DebitIncomeDoc уже является деактивированным: debitIncomeDocID:" + debitIncomeDoc.getId());
        }
        Balance balance = balanceService.findByShop(debitIncomeDoc.getShop());

        if (debitIncomeDoc.getDebitDoc() != null) {
            BigDecimal sumOfIncomesInIncomeDocList = BigDecimal.ZERO;
            for (DebitIncomeDoc previousDebitIncomeDoc : debitIncomeDoc.getDebitDoc().getDebitIncomeDocList()) {
                if (debitIncomeDoc.isActivate()) {
                    sumOfIncomesInIncomeDocList = sumOfIncomesInIncomeDocList.add(previousDebitIncomeDoc.getSum());
                }
            }
            if (debitIncomeDoc.getDebitDoc().getSumOfDebit().subtract(sumOfIncomesInIncomeDocList).add(debitIncomeDoc.getSum()).compareTo(BigDecimal.ZERO) != 0 ) {
                debitIncomeDoc.getDebitDoc().setReturned(false);
                debitIncomeDoc.getDebitDoc().setReturnFactDate(null);
                debitDocService.update(debitIncomeDoc.getDebitDoc());
            }
            if (!debitIncomeDoc.getDebitDoc().isWrittenOff()) {
                balance.setDebitSum(balance.getDebitSum().add(debitIncomeDoc.getSum()));
            }
        }

        if (debitIncomeDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().subtract(debitIncomeDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().subtract(debitIncomeDoc.getSum()));
        }
        balanceService.update(balance);
        debitIncomeDoc.setActivate(false);
        debitIncomeDoc.setDeactivateDate(LocalDate.now());
        debitIncomeDocDao.update(debitIncomeDoc);
        return debitIncomeDoc;
    }
}
