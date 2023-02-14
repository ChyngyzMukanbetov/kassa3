package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.DebitDocDao;
import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.service.abstracts.document.DebitDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DebitDocServiceImpl extends ReadWriteServiceImpl<DebitDoc, Long> implements DebitDocService {
    public DebitDocServiceImpl(DebitDocDao debitDocDao) {
        super(debitDocDao);
    }
}
