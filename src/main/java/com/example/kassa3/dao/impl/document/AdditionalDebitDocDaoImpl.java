package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.AdditionalDebitDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.AdditionalDebitDoc;
import org.springframework.stereotype.Repository;

@Repository
public class AdditionalDebitDocDaoImpl extends ReadWriteDaoImpl<AdditionalDebitDoc, Long> implements AdditionalDebitDocDao {
}
