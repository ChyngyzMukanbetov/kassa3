package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.AdditionalCreditDocDao;
import com.example.kassa3.model.document.AdditionalCreditDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.document.AdditionalCreditDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdditionalCreditDocServiceImpl extends ReadWriteServiceImpl<AdditionalCreditDoc, Long> implements AdditionalCreditDocService {

    private final AdditionalCreditDocDao additionalCreditDocDao;
    private final BalanceService balanceService;

    public AdditionalCreditDocServiceImpl(AdditionalCreditDocDao additionalCreditDocDao, BalanceService balanceService) {
        super(additionalCreditDocDao);
        this.additionalCreditDocDao = additionalCreditDocDao;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public AdditionalCreditDoc persist(AdditionalCreditDoc additionalCreditDoc) {

        if (!additionalCreditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1306", "Попытка сохранить(persist) неактивный(activate = false) additionalCreditDoc в Service");
        }
        Balance balance = balanceService.findByShop(additionalCreditDoc.getShop());
        balance.setCreditSum(balance.getCreditSum().add(additionalCreditDoc.getSumOfCredit()));
        balanceService.update(balance);
        additionalCreditDocDao.persist(additionalCreditDoc);
        return additionalCreditDoc;
    }
}
