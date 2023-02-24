package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ShopDao;
import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.service.abstracts.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {
    private final ShopDao shopDao;

    public ShopServiceImpl(ShopDao shopDao) {
        super(shopDao);
        this.shopDao = shopDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Shop> findAllActivateByUser(User user) {
        return shopDao.findAllActivateByUser(user);
    }
}
