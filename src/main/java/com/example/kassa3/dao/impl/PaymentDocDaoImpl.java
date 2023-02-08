package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.PaymentDocDao;
import com.example.kassa3.model.document.PaymentDoc;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDocDaoImpl extends ReadWriteDaoImpl<PaymentDoc, Long> implements PaymentDocDao {
}
