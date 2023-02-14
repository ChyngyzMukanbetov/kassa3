package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.PaymentDocResolver;
import com.example.kassa3.model.document.PaymentDoc;
import com.example.kassa3.model.dto.documentDto.PaymentDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PaymentDocResolver.class, PartnerConverter.class, UserConverter.class, ShopConverter.class, CreditDocConverter.class})
public interface PaymentDocCreateConverter {

    PaymentDoc toModel(Long paymentDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(source = "creditDocId", target = "creditDoc")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    PaymentDoc toModel(PaymentDocDto paymentDocDto);

    List<PaymentDoc> toModelList(List<PaymentDocDto> paymentDocDtoList);
}
