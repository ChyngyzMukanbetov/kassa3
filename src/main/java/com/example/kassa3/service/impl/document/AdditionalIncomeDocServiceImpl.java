package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.AdditionalIncomeDocDao;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.document.AdditionalDebitDocService;
import com.example.kassa3.service.abstracts.document.AdditionalIncomeDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AdditionalIncomeDocServiceImpl extends ReadWriteServiceImpl<AdditionalIncomeDoc, Long> implements AdditionalIncomeDocService {
    private final AdditionalIncomeDocDao additionalIncomeDocDao;
    private final AdditionalDebitDocService additionalDebitDocService;
    private final BalanceService balanceService;

    public AdditionalIncomeDocServiceImpl(AdditionalIncomeDocDao additionalIncomeDocDao, AdditionalDebitDocService additionalDebitDocService, BalanceService balanceService) {
        super(additionalIncomeDocDao);
        this.additionalIncomeDocDao = additionalIncomeDocDao;
        this.additionalDebitDocService = additionalDebitDocService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public AdditionalIncomeDoc persist(AdditionalIncomeDoc additionalIncomeDoc) {

        if (!additionalIncomeDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1308", "Попытка сохранить(persist) неактивный(activate = false) additionalIncomeDoc в Service");
        }

        Balance balance = balanceService.findByShop(additionalIncomeDoc.getShop());

        if (additionalIncomeDoc.getAdditionalDebitDoc() != null) {
            BigDecimal sumOfIncomesInIncomeDocList = BigDecimal.ZERO;
            for (AdditionalIncomeDoc previousAdditionalIncomeDoc : additionalIncomeDoc.getAdditionalDebitDoc().getAdditionalIncomeDocList()) {
                if (additionalIncomeDoc.isActivate()) {
                    sumOfIncomesInIncomeDocList = sumOfIncomesInIncomeDocList.add(previousAdditionalIncomeDoc.getSum());
                }
            }
            if (additionalIncomeDoc.getAdditionalDebitDoc().getSumOfDebit().subtract(sumOfIncomesInIncomeDocList).subtract(additionalIncomeDoc.getSum()).compareTo(BigDecimal.ZERO) == 0 ) {
                additionalIncomeDoc.getAdditionalDebitDoc().setReturned(true);
                additionalIncomeDoc.getAdditionalDebitDoc().setReturnFactDate(additionalIncomeDoc.getIncomeDate());
                additionalDebitDocService.update(additionalIncomeDoc.getAdditionalDebitDoc());
            }
            balance.setDebitSum(balance.getDebitSum().subtract(additionalIncomeDoc.getSum()));
        }

        if (additionalIncomeDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().add(additionalIncomeDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().add(additionalIncomeDoc.getSum()));
        }
        balanceService.update(balance);
        additionalIncomeDocDao.persist(additionalIncomeDoc);
        return additionalIncomeDoc;
    }

    @Transactional
    public AdditionalIncomeDoc deactivate(AdditionalIncomeDoc additionalIncomeDoc) {
        if (!additionalIncomeDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1605", "AdditionalIncomeDoc уже является деактивированным: additionalIncomeDocID:" + additionalIncomeDoc.getId());
        }
        Balance balance = balanceService.findByShop(additionalIncomeDoc.getShop());

        if (additionalIncomeDoc.getAdditionalDebitDoc() != null) {
            BigDecimal sumOfIncomesInIncomeDocList = BigDecimal.ZERO;
            for (AdditionalIncomeDoc previousAdditionalIncomeDoc : additionalIncomeDoc.getAdditionalDebitDoc().getAdditionalIncomeDocList()) {
                if (additionalIncomeDoc.isActivate()) {
                    sumOfIncomesInIncomeDocList = sumOfIncomesInIncomeDocList.add(previousAdditionalIncomeDoc.getSum());
                }
            }
            if (additionalIncomeDoc.getAdditionalDebitDoc().getSumOfDebit().subtract(sumOfIncomesInIncomeDocList).add(additionalIncomeDoc.getSum()).compareTo(BigDecimal.ZERO) != 0 ) {
                additionalIncomeDoc.getAdditionalDebitDoc().setReturned(false);
                additionalIncomeDoc.getAdditionalDebitDoc().setReturnFactDate(null);
                additionalDebitDocService.update(additionalIncomeDoc.getAdditionalDebitDoc());
            }
            if (!additionalIncomeDoc.getAdditionalDebitDoc().isWrittenOff()) {
                balance.setDebitSum(balance.getDebitSum().add(additionalIncomeDoc.getSum()));
            }
        }

        if (additionalIncomeDoc.isNonCash()) {
            balance.setNonCashSum(balance.getNonCashSum().subtract(additionalIncomeDoc.getSum()));
        } else {
            balance.setCashSum(balance.getCashSum().subtract(additionalIncomeDoc.getSum()));
        }
        balanceService.update(balance);
        additionalIncomeDoc.setActivate(false);
        additionalIncomeDoc.setDeactivateDate(LocalDate.now());
        additionalIncomeDocDao.update(additionalIncomeDoc);
        return additionalIncomeDoc;
    }
}