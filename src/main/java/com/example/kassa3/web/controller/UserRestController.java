package com.example.kassa3.web.controller;

import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.model.dto.UserCreateDto;
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
    public List<UserCreateDto> getAll() {
        return userConverter.toDTOList(userService.findAll());
    }

//    @PostMapping("/create")
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto, Principal principal) {
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto) {
//
//    }

}


//    public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleCreateDto articleCreateDto, Principal principal) {
//        if (articleCreateDto.getAuthorId() != userService.findByUsername(principal.getName()).getId()) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        Article article = articleCreateConverter.toModel(articleCreateDto);
//        articleService.save(article);
//        return new ResponseEntity<>(articleResponseConverter.toDto(articleService.getById(article.getId())), HttpStatus.OK);
//    }