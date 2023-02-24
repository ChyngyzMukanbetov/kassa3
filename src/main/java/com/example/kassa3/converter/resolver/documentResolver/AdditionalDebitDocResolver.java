package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.AdditionalDebitDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalDebitDocCreateDto;
import com.example.kassa3.service.abstracts.document.AdditionalDebitDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdditionalDebitDocResolver {
    private final AdditionalDebitDocService additionalDebitDocService;

    @ObjectFactory
    public AdditionalDebitDoc resolve(Long id, @TargetType Class<AdditionalDebitDoc> type) {
        AdditionalDebitDoc additionalDebitDoc;
        if (id == null) {
            return null;
        } else {
            additionalDebitDoc = additionalDebitDocService.findById(id);
        }
        return additionalDebitDoc;
    }

    @ObjectFactory
    public AdditionalDebitDoc resolve(AdditionalDebitDocCreateDto dto, @TargetType Class<AdditionalDebitDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new AdditionalDebitDoc();
        }
    }
}
