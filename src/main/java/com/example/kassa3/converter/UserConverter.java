package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.UserResolver;
import com.example.kassa3.model.dto.UserCreateDto;
import com.example.kassa3.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {UserResolver.class, PhoneConverter.class})
public interface UserConverter {

    User toModel(Long userId);

    @Mapping(source = "phone", target = "phoneDto")
    UserCreateDto toDTO(User user);

    @Mapping(target = "phone", source = "phoneDto")
    User toModel(UserCreateDto userDto);

    List<User> toModelList(List<UserCreateDto> userDtoList);

    List<UserCreateDto> toDTOList(List<User> userList);
}