package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemDao;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl extends ReadWriteDaoImpl<Item, Long> implements ItemDao {

    @Override
    public List<Item> findAllDeletedByUser(User user) {
        return null;
    }

    @Override
    public List<Item> findAllDeactivate() {
        return em.createQuery("select u from Item u where u.activate=false", Item.class).getResultList();
    }

    @Override
    public List<Item> findAllActivate() {
        return em.createQuery("select u from Item u where u.activate=true", Item.class).getResultList();
    }
}
