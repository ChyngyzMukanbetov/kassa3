package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.AdditionalDebitDocResolver;
import com.example.kassa3.model.document.AdditionalDebitDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalDebitDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {AdditionalDebitDocResolver.class, PartnerConverter.class,
                ShopConverter.class, UserCreateConverter.class})
public interface AdditionalDebitDocCreateConverter {

    AdditionalDebitDoc toModel(Long additionalDebitDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "returned")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(ignore = true, target = "additionalIncomeDocList")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    AdditionalDebitDoc toModel(AdditionalDebitDocCreateDto additionalDebitDocCreateDto);

    List<AdditionalDebitDoc> toModelList(List<AdditionalDebitDocCreateDto> debitDocCreateDtoList);
}
