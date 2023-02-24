package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface ItemReturnFromBayerDocService extends ReadWriteService<ItemReturnFromBayerDoc, Long> {
    ItemReturnFromBayerDoc deactivate(ItemReturnFromBayerDoc itemReturnFromBayerDoc);
}
