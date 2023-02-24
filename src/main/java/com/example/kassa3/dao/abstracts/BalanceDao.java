package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.model.entity.Shop;

public interface BalanceDao extends ReadWriteDao<Balance, Long> {
    Balance findByShop(Shop shop);
}
