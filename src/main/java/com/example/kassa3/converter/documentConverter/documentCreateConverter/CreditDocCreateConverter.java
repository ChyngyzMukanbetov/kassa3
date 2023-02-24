package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.CreditDocResolver;
import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {CreditDocResolver.class, PartnerConverter.class, ShopConverter.class, UserCreateConverter.class})
public interface CreditDocCreateConverter {

    CreditDoc toModel(Long creditDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "returned")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(ignore = true, target = "creditPaymentDocList")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(ignore = true, target = "itemArrivalDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    CreditDoc toModel(CreditDocCreateDto creditDocCreateDto);

    List<CreditDoc> toModelList(List<CreditDocCreateDto> creditDocCreateDtoList);
}
