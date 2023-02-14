package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemWriteOffDocDao;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemWriteOffDocServiceImpl extends ReadWriteServiceImpl<ItemWriteOffDoc, Long> implements ItemWriteOffDocService {
    public ItemWriteOffDocServiceImpl(ItemWriteOffDocDao itemWriteOffDocDao ) {
        super(itemWriteOffDocDao);
    }
}
