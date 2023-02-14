package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.User;

import java.util.List;

public interface ItemDao extends ReadWriteDao<Item, Long> {

    List<Item> findAllDeletedByUser(User user);

    List<Item> findAllDeactivate();

    List<Item> findAllActivate();

    List<Item> findActivateItemsByItemName(String itemName);
}
