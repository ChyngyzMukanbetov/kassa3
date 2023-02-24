package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface AdditionalIncomeDocService extends ReadWriteService<AdditionalIncomeDoc, Long> {
    AdditionalIncomeDoc deactivate(AdditionalIncomeDoc additionalIncomeDoc);
}
