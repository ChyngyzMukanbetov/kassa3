package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditPaymentDocCreateDto;
import com.example.kassa3.service.abstracts.document.CreditPaymentDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreditPaymentDocResolver {

    private final CreditPaymentDocService creditPaymentDocService;

    @ObjectFactory
    public CreditPaymentDoc resolve(Long id, @TargetType Class<CreditPaymentDoc> type) {
        CreditPaymentDoc creditPaymentDoc;
        if (id == null) {
            return null;
        } else {
            creditPaymentDoc = creditPaymentDocService.findById(id);
        }
        return creditPaymentDoc;
    }

    @ObjectFactory
    public CreditPaymentDoc resolve(CreditPaymentDocCreateDto dto, @TargetType Class<CreditPaymentDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new CreditPaymentDoc();
        }
    }
}
