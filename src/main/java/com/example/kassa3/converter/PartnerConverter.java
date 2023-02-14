package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.PartnerResolver;
import com.example.kassa3.model.dto.PartnerDto;
import com.example.kassa3.model.entity.Partner;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PartnerResolver.class, UserConverter.class})
public interface PartnerConverter {

    Partner toModel(Long partnerId);

    @Mapping(source = "user.id", target = "userId")
    PartnerDto toDTO(Partner partner);

    @Mapping(target = "user", source = "userId")
    Partner toModel(PartnerDto partnerDto);

    List<Partner> toModelList(List<PartnerDto> partnerDtoList);

    List<PartnerDto> toDTOList(List<Partner> partnerList);
}
