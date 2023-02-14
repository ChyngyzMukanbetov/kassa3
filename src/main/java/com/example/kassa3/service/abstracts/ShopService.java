package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;

import java.util.List;

public interface ShopService extends ReadWriteService<Shop, Long> {
    List<Shop> findAllActivateByUser(User user);
}
