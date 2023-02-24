package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.AdditionalCreditDocResolver;
import com.example.kassa3.model.document.AdditionalCreditDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalCreditDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {AdditionalCreditDocResolver.class, PartnerConverter.class, ShopConverter.class, UserCreateConverter.class})
public interface AdditionalCreditDocCreateConverter {

    AdditionalCreditDoc toModel(Long additionalCreditDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "returned")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(ignore = true, target = "additionalPaymentDocList")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    AdditionalCreditDoc toModel(AdditionalCreditDocCreateDto additionalCreditDocCreateDto);

    List<AdditionalCreditDoc> toModelList(List<AdditionalCreditDocCreateDto> additionalCreditDocCreateDtoList);
}