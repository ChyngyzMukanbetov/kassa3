package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.BalanceDao;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.model.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceDaoImpl extends ReadWriteDaoImpl<Balance, Long> implements BalanceDao {
    @Override
    public Balance findByShop(Shop shop) {
        return em.createQuery("select b from Balance b where b.shop=:shop", Balance.class)
                .setParameter("shop", shop).getSingleResult();
    }
}
