package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.InventorizationDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDocCreateDto;
import com.example.kassa3.service.abstracts.document.InventorizationDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InventorizationDocResolver {
    private final InventorizationDocService inventorizationDocService;

    @ObjectFactory
    public InventorizationDoc resolve(Long id, @TargetType Class<InventorizationDoc> type) {
        InventorizationDoc inventorizationDoc;
        if (id == null) {
            return null;
        } else {
            inventorizationDoc = inventorizationDocService.findById(id);
        }
        return inventorizationDoc;
    }

    @ObjectFactory
    public InventorizationDoc resolve(InventorizationDocCreateDto dto, @TargetType Class<InventorizationDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new InventorizationDoc();
        }
    }
}
