package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.InventorizationDetailsDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.InventorizationDetails;
import org.springframework.stereotype.Repository;

@Repository
public class InventorizationDetailsDaoImpl extends ReadWriteDaoImpl<InventorizationDetails, Long> implements InventorizationDetailsDao {
}
