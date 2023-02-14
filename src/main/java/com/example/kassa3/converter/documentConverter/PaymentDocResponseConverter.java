package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.model.document.PaymentDoc;
import com.example.kassa3.model.dto.documentDto.PaymentDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerConverter.class, UserConverter.class, ShopConverter.class})
public interface PaymentDocResponseConverter {

    @Mapping(target = "creditDocId", source = "creditDoc.id")
    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "partnerName", source = "partner.name")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    PaymentDocDto toDTO(PaymentDoc paymentDoc);

    List<PaymentDocDto> toDTOList(List<PaymentDoc> paymentDocList);
}
