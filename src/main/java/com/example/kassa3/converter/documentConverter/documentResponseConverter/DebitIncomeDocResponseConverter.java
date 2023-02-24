package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.DebitIncomeDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class})
public interface DebitIncomeDocResponseConverter {

    @Mapping(target = "debitDocId", source = "debitDoc.id")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    DebitIncomeDocResponseDto toDTO(DebitIncomeDoc debitIncomeDoc);

    List<DebitIncomeDocResponseDto> toDTOList(List<DebitIncomeDoc> debitIncomeDocList);
}
