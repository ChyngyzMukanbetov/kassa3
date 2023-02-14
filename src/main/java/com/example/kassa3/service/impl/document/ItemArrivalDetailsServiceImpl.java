package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemArrivalDetailsDao;
import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.service.abstracts.document.ItemArrivalDetailsService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemArrivalDetailsServiceImpl extends ReadWriteServiceImpl<ItemArrivalDetails, Long> implements ItemArrivalDetailsService {
    public ItemArrivalDetailsServiceImpl(ItemArrivalDetailsDao itemArrivalDetailsDao) {
        super(itemArrivalDetailsDao);
    }
}
