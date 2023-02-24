package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.DebitIncomeDocResolver;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitIncomeDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {DebitIncomeDocResolver.class, UserCreateConverter.class, ShopConverter.class, DebitDocCreateConverter.class})
public interface DebitIncomeDocCreateConverter {

    DebitIncomeDoc toModel(Long debitIncomeDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(source = "debitDocId", target = "debitDoc")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(ignore = true, target = "partner")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    DebitIncomeDoc toModel(DebitIncomeDocCreateDto debitIncomeDocCreateDto);

    List<DebitIncomeDoc> toModelList(List<DebitIncomeDocCreateDto> debitIncomeDocCreateDtoList);
}
