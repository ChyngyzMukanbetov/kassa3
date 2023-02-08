package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ItemArrivalDetailsDao;
import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.service.abstracts.ItemArrivalDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ItemArrivalDetailsServiceImpl extends ReadWriteServiceImpl<ItemArrivalDetails, Long> implements ItemArrivalDetailsService {
    public ItemArrivalDetailsServiceImpl(ItemArrivalDetailsDao itemArrivalDetailsDao) {
        super(itemArrivalDetailsDao);
    }
}
