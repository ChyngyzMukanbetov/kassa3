package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemDao;
import com.example.kassa3.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl extends ReadWriteDaoImpl<Item, Long> implements ItemDao {

    //    Поиск всех товаров в магазине
    @Override
    public List<Item> getItemsByShopId(Long shopId) {
        return em.createQuery(
                        "SELECT NEW com.amr.project.model.entity.Item(" +
                                "i.id, i.name, i.basePrice, i.price, i.count," +
                                "i.rating, i.description, i.discount)" +
                                "FROM Shop s JOIN s.items i WHERE s.id = :shopId", Item.class)
                .setParameter("shopId", shopId).getResultList();
    }

    @Override
    public List<Item> getItemList() {
        return  em.createQuery("select u from Item u order by u.rating desc", Item.class)
                .setMaxResults(4).getResultList();
    }
}
