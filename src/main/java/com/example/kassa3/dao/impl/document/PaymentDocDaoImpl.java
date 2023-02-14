package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.PaymentDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.PaymentDoc;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDocDaoImpl extends ReadWriteDaoImpl<PaymentDoc, Long> implements PaymentDocDao {
}
