package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.InventorizationDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDetailsCreateDto;
import com.example.kassa3.service.abstracts.document.InventorizationDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InventorizationDetailsResolver {
    private final InventorizationDetailsService inventorizationDetailsService;

    @ObjectFactory
    public InventorizationDetails resolve(Long id, @TargetType Class<InventorizationDetails> type) {
        InventorizationDetails inventorizationDetails;
        if (id == null) {
            return null;
        } else {
            inventorizationDetails = inventorizationDetailsService.findById(id);
        }
        return inventorizationDetails;
    }

    @ObjectFactory
    public InventorizationDetails resolve(InventorizationDetailsCreateDto dto, @TargetType Class<InventorizationDetails> type) {
        if (dto == null) {
            return null;
        } else {
            return new InventorizationDetails();
        }
    }
}
