package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemReturnToProviderDetailsDao;
import com.example.kassa3.model.document.ItemReturnToProviderDetails;
import com.example.kassa3.service.abstracts.document.ItemReturnToProviderDetailsService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemReturnToProviderDetailsServiceImpl extends ReadWriteServiceImpl<ItemReturnToProviderDetails, Long> implements ItemReturnToProviderDetailsService {
    public ItemReturnToProviderDetailsServiceImpl(ItemReturnToProviderDetailsDao itemReturnToProviderDetailsDao) {
        super(itemReturnToProviderDetailsDao);
    }
}
