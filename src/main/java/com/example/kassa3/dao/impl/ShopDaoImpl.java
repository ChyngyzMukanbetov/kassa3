package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ShopDao;
import com.example.kassa3.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDaoImpl extends ReadWriteDaoImpl<Shop, Long> implements ShopDao {
}