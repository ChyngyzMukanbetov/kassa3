package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.CreditPaymentDocResolver;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditPaymentDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {CreditPaymentDocResolver.class, UserCreateConverter.class, ShopConverter.class, CreditDocCreateConverter.class})
public interface CreditPaymentDocCreateConverter {

    CreditPaymentDoc toModel(Long creditPaymentDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(source = "creditDocId", target = "creditDoc")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(ignore = true, target = "partner")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    CreditPaymentDoc toModel(CreditPaymentDocCreateDto creditPaymentDocCreateDto);

    List<CreditPaymentDoc> toModelList(List<CreditPaymentDocCreateDto> creditPaymentDocCreateDtoList);
}
