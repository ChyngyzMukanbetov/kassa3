package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.AdditionalPaymentDocResolver;
import com.example.kassa3.model.document.AdditionalPaymentDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalPaymentDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {AdditionalPaymentDocResolver.class, UserCreateConverter.class, ShopConverter.class,
                AdditionalCreditDocCreateConverter.class, PartnerConverter.class})
public interface AdditionalPaymentDocCreateConverter {

    AdditionalPaymentDoc toModel(Long additionalPaymentDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "additionalCreditDocId", target = "additionalCreditDoc")
    @Mapping(source = "partnerId", target = "partner")
//    @Mapping(source = "partnerId", target = "partner", qualifiedByName = "ToModel")
//    @Mapping(target = "partner", defaultValue = "null")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    AdditionalPaymentDoc toModel(AdditionalPaymentDocCreateDto additionalPaymentDocCreateDto);

    List<AdditionalPaymentDoc> toModelList(List<AdditionalPaymentDocCreateDto> additionalPaymentDocCreateDtoList);
}
