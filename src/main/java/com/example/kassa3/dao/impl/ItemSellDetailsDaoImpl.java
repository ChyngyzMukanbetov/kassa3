package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemSellDetailsDao;
import com.example.kassa3.model.document.ItemSellDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemSellDetailsDaoImpl extends ReadWriteDaoImpl<ItemSellDetails, Long> implements ItemSellDetailsDao {
}
