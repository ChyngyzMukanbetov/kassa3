package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalIncomeDocCreateDto;
import com.example.kassa3.service.abstracts.document.AdditionalIncomeDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdditionalIncomeDocResolver {
    private final AdditionalIncomeDocService additionalIncomeDocService;

    @ObjectFactory
    public AdditionalIncomeDoc resolve(Long id, @TargetType Class<AdditionalIncomeDoc> type) {
        AdditionalIncomeDoc additionalIncomeDoc;
        if (id == null) {
            return null;
        } else {
            additionalIncomeDoc = additionalIncomeDocService.findById(id);
        }
        return additionalIncomeDoc;
    }

    @ObjectFactory
    public AdditionalIncomeDoc resolve(AdditionalIncomeDocCreateDto dto, @TargetType Class<AdditionalIncomeDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new AdditionalIncomeDoc();
        }
    }
}
