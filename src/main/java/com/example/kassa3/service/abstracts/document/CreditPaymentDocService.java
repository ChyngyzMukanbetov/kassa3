package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface CreditPaymentDocService extends ReadWriteService<CreditPaymentDoc, Long> {
    CreditPaymentDoc deactivate(CreditPaymentDoc creditPaymentDoc);
}
