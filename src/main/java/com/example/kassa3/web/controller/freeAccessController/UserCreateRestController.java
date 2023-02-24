package com.example.kassa3.web.controller.freeAccessController;

import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.model.dto.UserCreateDto;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.service.abstracts.EmailService;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/registration")
public class UserCreateRestController {

    private final EmailService emailService;
    private final UserService userService;
    private final UserCreateConverter userCreateConverter;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserCreateDto userCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        User user = userCreateConverter.toModel(userCreateDto);

        // check if user already exists
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("User с таким именем уже существует", HttpStatus.CONFLICT);
        }

        if (userService.existsByPhone(user.getPhone())) {
            return new ResponseEntity<>("User с таким телефоном уже существует", HttpStatus.CONFLICT);
        }

        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("User с таким email уже существует", HttpStatus.CONFLICT);
        }
        // save user to the database
//        userService.persist(user);
        // generate a confirmation token


        String confirmationToken = UUID.randomUUID().toString();
        user.setConfirmationToken(confirmationToken);

        user.setUserRegistrationDate(LocalDate.now());

        user.getPhone().setUser(user);

        Shop shop = new Shop();
        shop.setShopName("Основной");
        shop.setModerated(true);
        shop.setModerateAccept(true);
        shop.setUser(user);
        Balance balance = new Balance();
        balance.setShop(shop);
        shop.setBalance(balance);
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
