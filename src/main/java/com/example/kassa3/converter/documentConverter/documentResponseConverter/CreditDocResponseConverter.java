package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.CreditDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class, CreditPaymentDocResponseConverter.class})
public interface CreditDocResponseConverter {
    @Mapping(target = "itemArrivalDocId", source = "itemArrivalDoc.id")
    @Mapping(target = "creditPaymentDocList", source = "creditPaymentDocList")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    CreditDocResponseDto toDTO(CreditDoc creditDoc);

    List<CreditDocResponseDto> toDTOList(List<CreditDoc> creditDocList);
}
