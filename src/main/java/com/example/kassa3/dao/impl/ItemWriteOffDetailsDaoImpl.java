package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemWriteOffDetailsDao;
import com.example.kassa3.model.document.ItemWriteOffDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemWriteOffDetailsDaoImpl extends ReadWriteDaoImpl<ItemWriteOffDetails, Long> implements ItemWriteOffDetailsDao {
}
