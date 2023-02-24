package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditDocCreateDto;
import com.example.kassa3.service.abstracts.document.CreditDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreditDocResolver {
    private final CreditDocService creditDocService;

    @ObjectFactory
    public CreditDoc resolve(Long id, @TargetType Class<CreditDoc> type) {
        CreditDoc creditDoc;
        if (id == null) {
            return null;
        } else {
            creditDoc = creditDocService.findById(id);
        }
        return creditDoc;
    }

    @ObjectFactory
    public CreditDoc resolve(CreditDocCreateDto dto, @TargetType Class<CreditDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new CreditDoc();
        }
    }
}
