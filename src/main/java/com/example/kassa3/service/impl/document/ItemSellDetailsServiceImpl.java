package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemSellDetailsDao;
import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.service.abstracts.document.ItemSellDetailsService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemSellDetailsServiceImpl extends ReadWriteServiceImpl<ItemSellDetails, Long> implements ItemSellDetailsService {
    public ItemSellDetailsServiceImpl(ItemSellDetailsDao itemSellDetailsDao) {
        super(itemSellDetailsDao);
    }
}
