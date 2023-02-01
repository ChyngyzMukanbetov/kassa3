package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ItemAddDetailsDao;
import com.example.kassa3.model.entity.ItemAddDetails;
import com.example.kassa3.service.abstracts.ItemAddDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ItemAddDetailsServiceImpl extends ReadWriteServiceImpl<ItemAddDetails, Long> implements ItemAddDetailsService {
    public ItemAddDetailsServiceImpl(ItemAddDetailsDao itemAddDetailsDao) {
        super(itemAddDetailsDao);
    }
}
