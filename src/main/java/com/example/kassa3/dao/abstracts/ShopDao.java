package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;

import java.util.List;

public interface ShopDao extends ReadWriteDao<Shop, Long> {
    List<Shop> findAllActivateByUser(User user);
}
