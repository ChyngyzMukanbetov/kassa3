package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.IncomeDocDao;
import com.example.kassa3.model.document.IncomeDoc;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeDocDaoImpl extends ReadWriteDaoImpl<IncomeDoc, Long> implements IncomeDocDao {
}
