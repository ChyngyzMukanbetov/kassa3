package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ShopDao;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDaoImpl extends ReadWriteDaoImpl<Shop, Long> implements ShopDao {

    @Override
    public List<Shop> findAllActivateByUser(User user) {
        return em.createQuery("select s from Shop s where s.activate=true and s.user=:user", Shop.class)
                .setParameter("user", user).getResultList();
    }
}