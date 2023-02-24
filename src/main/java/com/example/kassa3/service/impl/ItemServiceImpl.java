package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ItemDao;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.service.abstracts.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        super(itemDao);
        this.itemDao = itemDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> findAllDeletedByUser(User user) {
        return itemDao.findAllDeletedByUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> findAllDeactivate() {
        return itemDao.findAllDeactivate();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> findAllActivate() {
        return itemDao.findAllActivate();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> findActivateItemsByItemName(String itemName) {
        return itemDao.findActivateItemsByItemName(itemName);
    }
}
