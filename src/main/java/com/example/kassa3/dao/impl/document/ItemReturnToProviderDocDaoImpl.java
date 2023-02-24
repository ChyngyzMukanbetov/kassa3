package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemReturnToProviderDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import org.springframework.stereotype.Repository;

@Repository
public class ItemReturnToProviderDocDaoImpl extends ReadWriteDaoImpl<ItemReturnToProviderDoc, Long> implements ItemReturnToProviderDocDao {
}
