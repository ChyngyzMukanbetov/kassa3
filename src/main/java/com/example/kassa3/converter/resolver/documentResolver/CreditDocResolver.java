package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.dto.documentDto.CreditDocDto;
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
    public CreditDoc resolve(CreditDocDto dto, @TargetType Class<CreditDoc> type) {
        CreditDoc creditDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            creditDoc = new CreditDoc();
        } else {
            creditDoc = creditDocService.findById(dto.getId());
        }
        return creditDoc;
    }
}
