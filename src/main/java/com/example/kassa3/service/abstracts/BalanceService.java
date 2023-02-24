package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.model.entity.Shop;

public interface BalanceService extends ReadWriteService<Balance, Long> {
    Balance findByShop(Shop shop);
}
