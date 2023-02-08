package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemSellDocDao;
import com.example.kassa3.model.document.ItemSellDoc;
import org.springframework.stereotype.Repository;

@Repository
public class ItemSellDocDaoImpl extends ReadWriteDaoImpl<ItemSellDoc, Long> implements ItemSellDocDao {
}
