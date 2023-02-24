package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.AdditionalCreditDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.AdditionalCreditDoc;
import org.springframework.stereotype.Repository;

@Repository
public class AdditionalCreditDocDaoImpl extends ReadWriteDaoImpl<AdditionalCreditDoc, Long> implements AdditionalCreditDocDao {
}
