package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.InventorizationDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface InventorizationDocService extends ReadWriteService<InventorizationDoc, Long> {
    InventorizationDoc deactivate(InventorizationDoc inventorizationDoc);
}
