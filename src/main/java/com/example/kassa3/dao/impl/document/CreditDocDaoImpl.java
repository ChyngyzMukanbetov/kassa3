package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.CreditDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.CreditDoc;
import org.springframework.stereotype.Repository;

@Repository
public class CreditDocDaoImpl extends ReadWriteDaoImpl<CreditDoc, Long> implements CreditDocDao {
}
