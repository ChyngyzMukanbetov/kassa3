package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemWriteOffDocDao;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import org.springframework.stereotype.Repository;

@Repository
public class ItemWriteOffDocDaoImpl extends ReadWriteDaoImpl<ItemWriteOffDoc, Long> implements ItemWriteOffDocDao {
}
