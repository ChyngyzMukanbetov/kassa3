package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.CreditPaymentDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class})
public interface CreditPaymentDocResponseConverter {

    @Mapping(target = "creditDocId", source = "creditDoc.id")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    CreditPaymentDocResponseDto toDTO(CreditPaymentDoc creditPaymentDoc);

    List<CreditPaymentDocResponseDto> toDTOList(List<CreditPaymentDoc> creditPaymentDocList);
}
