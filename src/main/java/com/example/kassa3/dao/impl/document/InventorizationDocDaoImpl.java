package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.InventorizationDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.InventorizationDoc;
import org.springframework.stereotype.Repository;

@Repository
public class InventorizationDocDaoImpl extends ReadWriteDaoImpl<InventorizationDoc, Long> implements InventorizationDocDao {
}
