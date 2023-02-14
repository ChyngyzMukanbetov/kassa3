package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemWriteOffDetailsDao;
import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDetailsService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemWriteOffDetailsServiceImpl extends ReadWriteServiceImpl<ItemWriteOffDetails, Long> implements ItemWriteOffDetailsService {
    public ItemWriteOffDetailsServiceImpl(ItemWriteOffDetailsDao itemWriteOffDetailsDao ) {
        super(itemWriteOffDetailsDao);
    }
}
