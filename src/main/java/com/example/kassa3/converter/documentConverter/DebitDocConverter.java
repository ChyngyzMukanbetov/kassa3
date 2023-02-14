package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.DebitDocResolver;
import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.model.dto.documentDto.DebitDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {DebitDocResolver.class, PartnerConverter.class, IncomeDocResponseConverter.class, ShopConverter.class, UserConverter.class})
public interface DebitDocConverter {

    DebitDoc toModel(Long debitDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(ignore = true, target = "incomeDocList")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(ignore = true, target = "itemSellDoc")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "returned", defaultValue = "false")
    DebitDoc toModel(DebitDocDto debitDocDto);

    @Mapping(target = "itemSellDocId", source = "itemSellDoc.id")
    @Mapping(target = "incomeDocList", source = "incomeDocList")
    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "partnerName", source = "partner.name")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    DebitDocDto toDTO(DebitDoc debitDoc);

    List<DebitDoc> toModelList(List<DebitDocDto> debitDocDtoList);

    List<DebitDocDto> toDTOList(List<DebitDoc> debitDocList);
}
