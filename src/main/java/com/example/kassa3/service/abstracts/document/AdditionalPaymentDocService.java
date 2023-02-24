package com.example.kassa3.service.abstracts.document;

import com.example.kassa3.model.document.AdditionalPaymentDoc;
import com.example.kassa3.service.abstracts.ReadWriteService;

public interface AdditionalPaymentDocService extends ReadWriteService<AdditionalPaymentDoc, Long> {
    AdditionalPaymentDoc deactivate(AdditionalPaymentDoc additionalPaymentDoc);
}
