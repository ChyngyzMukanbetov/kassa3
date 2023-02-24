package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.BalanceDao;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.service.abstracts.BalanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceServiceImpl extends ReadWriteServiceImpl<Balance, Long> implements BalanceService {

    private final BalanceDao balanceDao;

    public BalanceServiceImpl(BalanceDao balanceDao) {
        super(balanceDao);
        this.balanceDao = balanceDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Balance findByShop(Shop shop) {
        return balanceDao.findByShop(shop);
    }
}
