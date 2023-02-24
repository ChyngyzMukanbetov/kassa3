package com.example.kassa3.web.controller.freeAccessController;

import com.example.kassa3.converter.UserResponseConverter;
import com.example.kassa3.model.dto.UserResponseDto;
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
    private final UserResponseConverter userResponseConverter;

    @GetMapping("/getAll")
    public List<UserResponseDto> getAll() {
        return userResponseConverter.toDTOList(userService.findAll());
    }

}
