package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface ItemReturnToProviderDocService extends ReadWriteService<ItemReturnToProviderDoc, Long> {
    ItemReturnToProviderDoc deactivate(ItemReturnToProviderDoc itemReturnToProviderDoc);
}
