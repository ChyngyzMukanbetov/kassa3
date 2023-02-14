package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.CreditDocResolver;
import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.dto.documentDto.CreditDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {CreditDocResolver.class, PartnerConverter.class, PaymentDocResponseConverter.class, ShopConverter.class, UserConverter.class})
public interface CreditDocConverter {

    CreditDoc toModel(Long creditDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(ignore = true, target = "paymentDocList")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(ignore = true, target = "itemArrivalDoc")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "returned", defaultValue = "false")
    CreditDoc toModel(CreditDocDto creditDocDto);

    @Mapping(target = "itemArrivalDocId", source = "itemArrivalDoc.id")
    @Mapping(target = "paymentDocList", source = "paymentDocList")
    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "partnerName", source = "partner.name")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    CreditDocDto toDTO(CreditDoc creditDoc);

    List<CreditDoc> toModelList(List<CreditDocDto> creditDocDtoList);

    List<CreditDocDto> toDTOList(List<CreditDoc> creditDocList);
}
