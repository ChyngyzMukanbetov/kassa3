package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalIncomeDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class})
public interface AdditionalIncomeDocResponseConverter {

    @Mapping(target = "additionalDebitDocId", source = "additionalDebitDoc.id")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    AdditionalIncomeDocResponseDto toDTO(AdditionalIncomeDoc additionalIncomeDoc);

    List<AdditionalIncomeDocResponseDto> toDTOList(List<AdditionalIncomeDoc> additionalIncomeDocList);
}
