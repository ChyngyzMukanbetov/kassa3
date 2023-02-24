package com.example.kassa3.converter;

import com.example.kassa3.model.dto.UserResponseDto;
import com.example.kassa3.model.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {PhoneConverter.class})
public interface UserResponseConverter {

    @Mapping(source = "phone", target = "phoneDto")
    @Mapping(source = "gender.rusName", target = "genderName")
    UserResponseDto toDTO(User user);

    List<UserResponseDto> toDTOList(List<User> userList);
}
