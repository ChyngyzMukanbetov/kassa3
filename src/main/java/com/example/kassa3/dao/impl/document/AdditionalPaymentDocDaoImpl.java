package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.AdditionalPaymentDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.AdditionalPaymentDoc;
import org.springframework.stereotype.Repository;

@Repository
public class AdditionalPaymentDocDaoImpl extends ReadWriteDaoImpl<AdditionalPaymentDoc, Long> implements AdditionalPaymentDocDao {
}
