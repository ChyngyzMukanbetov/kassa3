package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.UserResolver;
import com.example.kassa3.model.dto.UserDto;
import com.example.kassa3.model.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PhoneConverter.class, UserResolver.class})
public interface UserConverter {
    User toModel(Long userId);

    @Mapping(source = "phone", target = "phoneDto")
    UserDto toDTO(User user);

    List<UserDto> toDTOList(List<User> userList);
}
