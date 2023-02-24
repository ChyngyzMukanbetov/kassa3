package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.AdditionalPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalPaymentDocCreateDto;
import com.example.kassa3.service.abstracts.document.AdditionalPaymentDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdditionalPaymentDocResolver {
    private final AdditionalPaymentDocService additionalPaymentDocService;

    @ObjectFactory
    public AdditionalPaymentDoc resolve(Long id, @TargetType Class<AdditionalPaymentDoc> type) {
        AdditionalPaymentDoc additionalPaymentDoc;
        if (id == null) {
            return null;
        } else {
            additionalPaymentDoc = additionalPaymentDocService.findById(id);
        }
        return additionalPaymentDoc;
    }

    @ObjectFactory
    public AdditionalPaymentDoc resolve(AdditionalPaymentDocCreateDto dto, @TargetType Class<AdditionalPaymentDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new AdditionalPaymentDoc();
        }
    }
}
