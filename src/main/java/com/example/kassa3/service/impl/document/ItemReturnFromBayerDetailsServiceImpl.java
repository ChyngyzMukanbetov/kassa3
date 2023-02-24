package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemReturnFromBayerDetailsDao;
import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import com.example.kassa3.service.abstracts.document.ItemReturnFromBayerDetailsService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemReturnFromBayerDetailsServiceImpl extends ReadWriteServiceImpl<ItemReturnFromBayerDetails, Long> implements ItemReturnFromBayerDetailsService {
    public ItemReturnFromBayerDetailsServiceImpl(ItemReturnFromBayerDetailsDao itemReturnFromBayerDetailsDao) {
        super(itemReturnFromBayerDetailsDao);
    }
}
