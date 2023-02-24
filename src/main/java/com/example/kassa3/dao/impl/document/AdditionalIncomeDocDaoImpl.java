package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.AdditionalIncomeDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import org.springframework.stereotype.Repository;

@Repository
public class AdditionalIncomeDocDaoImpl extends ReadWriteDaoImpl<AdditionalIncomeDoc, Long> implements AdditionalIncomeDocDao {
}
