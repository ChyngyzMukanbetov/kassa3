package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemReturnFromBayerDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import org.springframework.stereotype.Repository;

@Repository
public class ItemReturnFromBayerDocDaoImpl extends ReadWriteDaoImpl<ItemReturnFromBayerDoc, Long> implements ItemReturnFromBayerDocDao {
}
