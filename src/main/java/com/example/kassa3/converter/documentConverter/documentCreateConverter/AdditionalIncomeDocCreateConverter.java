package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.AdditionalIncomeDocResolver;
import com.example.kassa3.model.document.AdditionalIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalIncomeDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {AdditionalIncomeDocResolver.class, UserCreateConverter.class, ShopConverter.class,
                AdditionalDebitDocCreateConverter.class, PartnerConverter.class})
public interface AdditionalIncomeDocCreateConverter {

    AdditionalIncomeDoc toModel(Long additionalIncomeDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "additionalDebitDocId", target = "additionalDebitDoc")
    @Mapping(source = "partnerId", target = "partner")
//    @Mapping(target = "partner", defaultValue = "null")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    AdditionalIncomeDoc toModel(AdditionalIncomeDocCreateDto additionalIncomeDocCreateDto);

    List<AdditionalIncomeDoc> toModelList(List<AdditionalIncomeDocCreateDto> additionalIncomeDocCreateDtoList);
}
