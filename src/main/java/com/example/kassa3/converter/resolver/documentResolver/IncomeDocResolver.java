package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.IncomeDoc;
import com.example.kassa3.model.dto.documentDto.IncomeDocDto;
import com.example.kassa3.service.abstracts.document.IncomeDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IncomeDocResolver {

    private final IncomeDocService incomeDocService;

    @ObjectFactory
    public IncomeDoc resolve(IncomeDocDto dto, @TargetType Class<IncomeDoc> type) {
        IncomeDoc incomeDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            incomeDoc = new IncomeDoc();
        } else {
            incomeDoc = incomeDocService.findById(dto.getId());
        }
        return incomeDoc;
    }
}
