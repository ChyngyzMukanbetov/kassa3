package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemWriteOffDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import org.springframework.stereotype.Repository;

@Repository
public class ItemWriteOffDocDaoImpl extends ReadWriteDaoImpl<ItemWriteOffDoc, Long> implements ItemWriteOffDocDao {
}
