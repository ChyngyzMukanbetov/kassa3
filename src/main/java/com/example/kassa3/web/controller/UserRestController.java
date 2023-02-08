package com.example.kassa3.web.controller;

import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.model.dto.UserDto;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
//@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/getAll")
    public List<UserDto> getAll() {
        return userConverter.toDTOList(userService.findAll());
    }

}
