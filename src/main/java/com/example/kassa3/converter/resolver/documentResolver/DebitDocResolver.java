package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.model.dto.documentDto.DebitDocDto;
import com.example.kassa3.service.abstracts.document.DebitDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DebitDocResolver {
    private final DebitDocService debitDocService;

    @ObjectFactory
    public DebitDoc resolve(DebitDocDto dto, @TargetType Class<DebitDoc> type) {
        DebitDoc debitDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            debitDoc = new DebitDoc();
        } else {
            debitDoc = debitDocService.findById(dto.getId());
        }
        return debitDoc;
    }
}
