package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.AddressResolver;
import com.example.kassa3.model.dto.AddressDto;
import com.example.kassa3.model.entity.Address;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = {CityConverter.class, AddressResolver.class})
public interface AddressConverter {

    Address toModel(Long addressId);

    @Mapping(source = "city", target = "city")
    AddressDto toDTO(Address address);

    @Mapping(target = "city", source = "city")
    Address toModel(AddressDto addressDto);

    List<Address> toModelList(List<AddressDto> addressDtoList);

    List<AddressDto> toDTOList(List<Address> addressList);
}


