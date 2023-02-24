package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface DebitIncomeDocService extends ReadWriteService<DebitIncomeDoc, Long> {
    DebitIncomeDoc deactivate(DebitIncomeDoc debitIncomeDoc);
}
