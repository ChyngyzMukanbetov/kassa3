package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.CreditPaymentDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.CreditPaymentDoc;
import org.springframework.stereotype.Repository;

@Repository
public class CreditPaymentDocDaoImpl extends ReadWriteDaoImpl<CreditPaymentDoc, Long> implements CreditPaymentDocDao {
}
