package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitIncomeDocCreateDto;
import com.example.kassa3.service.abstracts.document.DebitIncomeDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DebitIncomeDocResolver {

    private final DebitIncomeDocService debitIncomeDocService;

    @ObjectFactory
    public DebitIncomeDoc resolve(Long id, @TargetType Class<DebitIncomeDoc> type) {
        DebitIncomeDoc debitIncomeDoc;
        if (id == null) {
            return null;
        } else {
            debitIncomeDoc = debitIncomeDocService.findById(id);
        }
        return debitIncomeDoc;
    }

    @ObjectFactory
    public DebitIncomeDoc resolve(DebitIncomeDocCreateDto dto, @TargetType Class<DebitIncomeDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new DebitIncomeDoc();
        }
    }
}
