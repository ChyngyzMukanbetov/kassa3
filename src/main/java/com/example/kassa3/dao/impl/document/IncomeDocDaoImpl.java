package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.IncomeDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.IncomeDoc;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeDocDaoImpl extends ReadWriteDaoImpl<IncomeDoc, Long> implements IncomeDocDao {
}
