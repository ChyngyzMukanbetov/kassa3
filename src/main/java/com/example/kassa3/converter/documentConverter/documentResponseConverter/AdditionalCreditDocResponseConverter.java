package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.AdditionalCreditDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalCreditDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class, AdditionalPaymentDocResponseConverter.class})
public interface AdditionalCreditDocResponseConverter {

    @Mapping(target = "additionalPaymentDocList", source = "additionalPaymentDocList")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    AdditionalCreditDocResponseDto toDTO(AdditionalCreditDoc additionalCreditDoc);

    List<AdditionalCreditDocResponseDto> toDTOList(List<AdditionalCreditDoc> additionalCreditDocList);
}
