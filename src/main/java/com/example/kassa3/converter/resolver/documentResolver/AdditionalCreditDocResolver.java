package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.AdditionalCreditDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalCreditDocCreateDto;
import com.example.kassa3.service.abstracts.document.AdditionalCreditDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdditionalCreditDocResolver {
    private final AdditionalCreditDocService additionalCreditDocService;

    @ObjectFactory
    public AdditionalCreditDoc resolve(Long id, @TargetType Class<AdditionalCreditDoc> type) {
        AdditionalCreditDoc additionalCreditDoc;
        if (id == null) {
            return null;
        } else {
            additionalCreditDoc = additionalCreditDocService.findById(id);
        }
        return additionalCreditDoc;
    }

    @ObjectFactory
    public AdditionalCreditDoc resolve(AdditionalCreditDocCreateDto dto, @TargetType Class<AdditionalCreditDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new AdditionalCreditDoc();
        }
    }
}
