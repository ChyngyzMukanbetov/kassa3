package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitDocCreateDto;
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
    public DebitDoc resolve(Long id, @TargetType Class<DebitDoc> type) {
        DebitDoc debitDoc;
        if (id == null) {
            return null;
        } else {
            debitDoc = debitDocService.findById(id);
        }
        return debitDoc;
    }

    @ObjectFactory
    public DebitDoc resolve(DebitDocCreateDto dto, @TargetType Class<DebitDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new DebitDoc();
        }
    }
}
