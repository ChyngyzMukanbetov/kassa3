package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.IncomeDocResolver;
import com.example.kassa3.model.document.IncomeDoc;
import com.example.kassa3.model.dto.documentDto.IncomeDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {IncomeDocResolver.class, PartnerConverter.class, UserConverter.class, ShopConverter.class, DebitDocConverter.class})
public interface IncomeDocCreateConverter {

    IncomeDoc toModel(Long incomeDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(source = "debitDocId", target = "debitDoc")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    IncomeDoc toModel(IncomeDocDto incomeDocDto);

    List<IncomeDoc> toModelList(List<IncomeDocDto> incomeDocDtoList);
}
