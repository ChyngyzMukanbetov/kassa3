package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.AdditionalDebitDocDao;
import com.example.kassa3.model.document.AdditionalDebitDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.document.AdditionalDebitDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdditionalDebitDocServiceImpl extends ReadWriteServiceImpl<AdditionalDebitDoc, Long> implements AdditionalDebitDocService {
    private final AdditionalDebitDocDao additionalDebitDocDao;
    private final BalanceService balanceService;

    public AdditionalDebitDocServiceImpl(AdditionalDebitDocDao additionalDebitDocDao, BalanceService balanceService) {
        super(additionalDebitDocDao);
        this.additionalDebitDocDao = additionalDebitDocDao;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public AdditionalDebitDoc persist(AdditionalDebitDoc additionalDebitDoc) {

        if (!additionalDebitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1307", "Попытка сохранить(persist) неактивный(activate = false) additionalDebitDoc в Service");
        }
        Balance balance = balanceService.findByShop(additionalDebitDoc.getShop());
        balance.setCreditSum(balance.getCreditSum().add(additionalDebitDoc.getSumOfDebit()));
        balanceService.update(balance);
        additionalDebitDocDao.persist(additionalDebitDoc);
        return additionalDebitDoc;
    }
}