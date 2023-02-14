package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemSellDocDao;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.service.abstracts.document.ItemSellDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemSellDocServiceImpl extends ReadWriteServiceImpl<ItemSellDoc, Long> implements ItemSellDocService {
    public ItemSellDocServiceImpl(ItemSellDocDao itemSellDocDao) {
        super(itemSellDocDao);
    }
}
