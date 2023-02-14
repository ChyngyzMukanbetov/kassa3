package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.UserResolver;
import com.example.kassa3.model.dto.UserCreateDto;
import com.example.kassa3.model.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PhoneConverter.class, UserResolver.class, GenderConverter.class})
public interface UserCreateConverter {
    @Mapping(target = "phone", source = "phoneDto")
    @Mapping(target = "gender", source = "genderName")
    User toModel(UserCreateDto userCreateDto);
}