package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.DebitDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class, DebitIncomeDocResponseConverter.class})
public interface DebitDocResponseConverter {

    @Mapping(target = "itemSellDocId", source = "itemSellDoc.id")
    @Mapping(target = "debitIncomeDocList", source = "debitIncomeDocList")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    DebitDocResponseDto toDTO(DebitDoc debitDoc);

    List<DebitDocResponseDto> toDTOList(List<DebitDoc> debitDocList);
}
