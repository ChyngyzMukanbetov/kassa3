package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ImageDao;
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
    private final ImageDao imageDao;

    public ItemServiceImpl(ItemDao itemDao, ImageDao imageDao) {
        super(itemDao);
        this.itemDao = itemDao;
        this.imageDao = imageDao;
    }

    @Transactional
    @Override
    public List<Item> findAllDeletedByUser(User user) {
        return itemDao.findAllDeletedByUser(user);
    }

    @Transactional
    @Override
    public List<Item> findAllDeactivate() {
        return itemDao.findAllDeactivate();
    }

    @Override
    public List<Item> findAllActivate() {
        return itemDao.findAllActivate();
    }
}
