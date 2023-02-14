package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.IncomeDocDao;
import com.example.kassa3.model.document.IncomeDoc;
import com.example.kassa3.service.abstracts.document.IncomeDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class IncomeDocServiceImpl extends ReadWriteServiceImpl<IncomeDoc, Long> implements IncomeDocService {
    public IncomeDocServiceImpl(IncomeDocDao incomeDocDao) {
        super(incomeDocDao);
    }
}
