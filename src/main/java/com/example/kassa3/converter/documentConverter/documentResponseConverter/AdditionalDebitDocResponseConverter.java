package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.AdditionalDebitDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalDebitDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class, AdditionalIncomeDocResponseConverter.class})
public interface AdditionalDebitDocResponseConverter {

    @Mapping(target = "additionalIncomeDocList", source = "additionalIncomeDocList")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    AdditionalDebitDocResponseDto toDTO(AdditionalDebitDoc additionalDebitDoc);

    List<AdditionalDebitDocResponseDto> toDTOList(List<AdditionalDebitDoc> additionalDebitDocList);
}
