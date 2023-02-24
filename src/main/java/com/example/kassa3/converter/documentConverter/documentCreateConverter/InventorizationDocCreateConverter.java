package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.InventorizationDocResolver;
import com.example.kassa3.model.document.InventorizationDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {InventorizationDocResolver.class, InventorizationDetailsCreateConverter.class,
                ShopConverter.class, UserCreateConverter.class})
public interface InventorizationDocCreateConverter {

    InventorizationDoc toModel(Long itemArrivalDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "inventorizationDetailsList", target = "inventorizationDetailsList")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    InventorizationDoc toModel(InventorizationDocCreateDto inventorizationDocCreateDto);

    List<InventorizationDoc> toModelList(List<InventorizationDocCreateDto> inventorizationDocCreateDtoList);
}
