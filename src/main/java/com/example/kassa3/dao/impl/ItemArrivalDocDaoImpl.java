package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemArrivalDocDao;
import com.example.kassa3.model.document.ItemArrivalDoc;
import org.springframework.stereotype.Repository;

@Repository
public class ItemArrivalDocDaoImpl extends ReadWriteDaoImpl<ItemArrivalDoc, Long> implements ItemArrivalDocDao {
}
