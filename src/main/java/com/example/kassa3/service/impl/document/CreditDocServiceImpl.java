package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.CreditDocDao;
import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.service.abstracts.document.CreditDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CreditDocServiceImpl extends ReadWriteServiceImpl<CreditDoc, Long> implements CreditDocService {
    public CreditDocServiceImpl(CreditDocDao creditDocDao) {
        super(creditDocDao);
    }
}
