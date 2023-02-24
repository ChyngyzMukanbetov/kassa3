package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.DebitIncomeDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.DebitIncomeDoc;
import org.springframework.stereotype.Repository;

@Repository
public class DebitIncomeDocDaoImpl extends ReadWriteDaoImpl<DebitIncomeDoc, Long> implements DebitIncomeDocDao {
}
