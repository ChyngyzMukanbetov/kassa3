package com.example.kassa3.web.controller;

import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.model.dto.UserCreateDto;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.service.abstracts.EmailService;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/registration")
public class UserCreateRestController {

    private EmailService emailService;
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserCreateDto userDto) {

        User user = userConverter.toModel(userDto);

        // check if user already exists
        if(userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("User с таким именем уже существует", HttpStatus.CONFLICT);
        }

        if(userService.existsByPhone(user.getPhone())) {
            return new ResponseEntity<>("User с таким телефоном уже существует", HttpStatus.CONFLICT);
        }
        // save user to the database
        userService.persist(user);
        // generate a confirmation token
        String confirmationToken = UUID.randomUUID().toString();
        user.setConfirmationToken(confirmationToken);

        user.setUserRegistrationDate(LocalDate.now());

        Shop shop = new Shop();
        shop.setShopName("Основной");
        shop.setModerated(true);
        shop.setModerateAccept(true);
        Set<Shop> shops = new HashSet<>();
        shops.add(shop);
        user.setShops(shops);

        userService.persist(user);
        // send a confirmation email to the user
//        emailService.sendConfirmationEmail(user.getEmail(), confirmationToken);
        return new ResponseEntity<>("User registered successfully. Please check your email for confirmation.", HttpStatus.CREATED);
    }

    @PostMapping("/confirm/{token}")
    public ResponseEntity<String> confirmUser(@PathVariable String token) {
        User user = userService.findByConfirmationToken(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setConfirm(true);
        userService.persist(user);
        return new ResponseEntity<>("User confirmed successfully", HttpStatus.OK);
    }
}
