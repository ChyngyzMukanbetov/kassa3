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
        return em.createQuery("select i from Item i where i.activate=false", Item.class).getResultList();
    }

    @Override
    public List<Item> findAllActivate() {
        return em.createQuery("select i from Item i where i.activate=true", Item.class).getResultList();
    }

    @Override
    public List<Item> findActivateItemsByItemName(String itemName) {
        return em.createQuery("SELECT i FROM Item i WHERE i.activate = true AND UPPER(i.itemName) LIKE UPPER(:itemName)", Item.class)
                .setParameter("itemName", "%" + itemName + "%")
                .getResultList();
    }
}
