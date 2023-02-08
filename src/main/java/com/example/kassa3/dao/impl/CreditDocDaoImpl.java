package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.CreditDocDao;
import com.example.kassa3.model.document.CreditDoc;
import org.springframework.stereotype.Repository;

@Repository
public class CreditDocDaoImpl extends ReadWriteDaoImpl<CreditDoc, Long> implements CreditDocDao {
}
