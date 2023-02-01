package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.PhoneResolver;
import com.example.kassa3.model.dto.PhoneDto;
import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.enums.PhoneCode;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {PhoneResolver.class, PhoneCodeConverter.class})
public interface PhoneConverter {

    @Mapping(source = "phoneCode.code", target = "phoneCodeCode")
    PhoneDto toDTO(Phone phone);

    @Mapping(source = "phoneCodeCode", target = "phoneCode")
    Phone toModel(PhoneDto phoneDto);

    List<Phone> toModelList(List<PhoneDto> phoneDtoList);

    List<PhoneDto> toDTOList(List<Phone> phoneList);
}
