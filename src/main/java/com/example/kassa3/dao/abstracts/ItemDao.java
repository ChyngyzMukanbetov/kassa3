package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Item;

import java.util.List;

public interface ItemDao extends ReadWriteDao<Item, Long> {

    List<Item> getItemsByShopId(Long shopId);

    List<Item> getItemList();
}
