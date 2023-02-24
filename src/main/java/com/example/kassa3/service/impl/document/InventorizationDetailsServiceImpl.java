package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.InventorizationDetailsDao;
import com.example.kassa3.model.document.InventorizationDetails;
import com.example.kassa3.service.abstracts.document.InventorizationDetailsService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class InventorizationDetailsServiceImpl extends ReadWriteServiceImpl<InventorizationDetails, Long> implements InventorizationDetailsService {
    public InventorizationDetailsServiceImpl(InventorizationDetailsDao inventorizationDetailsDao) {
        super(inventorizationDetailsDao);
    }
}
