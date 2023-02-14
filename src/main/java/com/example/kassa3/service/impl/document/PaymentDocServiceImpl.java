package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.PaymentDocDao;
import com.example.kassa3.model.document.PaymentDoc;
import com.example.kassa3.service.abstracts.document.PaymentDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PaymentDocServiceImpl extends ReadWriteServiceImpl<PaymentDoc, Long> implements PaymentDocService {
    public PaymentDocServiceImpl(PaymentDocDao paymentDocDao ) {
        super(paymentDocDao);
    }
}
