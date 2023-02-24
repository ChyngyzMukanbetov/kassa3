package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.DebitDocResolver;
import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {DebitDocResolver.class, PartnerConverter.class,
                ShopConverter.class, UserCreateConverter.class})
public interface DebitDocCreateConverter {

    DebitDoc toModel(Long debitDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "returned")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(ignore = true, target = "debitIncomeDocList")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(ignore = true, target = "itemSellDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    DebitDoc toModel(DebitDocCreateDto debitDocCreateDto);

    List<DebitDoc> toModelList(List<DebitDocCreateDto> debitDocCreateDtoList);

}
