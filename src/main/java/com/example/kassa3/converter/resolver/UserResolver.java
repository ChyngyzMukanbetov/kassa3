package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.UserCreateDto;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResolver {

    private final UserService userService;

    @ObjectFactory
    public User resolve(Long id, @TargetType Class<User> type) {
        User user;
        if (id == null) {
            return null;
        } else {
            user = userService.findById(id);
        }
        return user;
    }

    @ObjectFactory
    public User resolve(UserCreateDto dto, @TargetType Class<User> type) {
        User user;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            user = new User();
        } else {
            user = userService.findById(dto.getId());
        }
        return user;
    }
}
