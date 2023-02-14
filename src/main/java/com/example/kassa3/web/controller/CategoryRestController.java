package com.example.kassa3.web.controller;

import com.example.kassa3.converter.CategoryConverter;
import com.example.kassa3.model.dto.CategoryDto;
import com.example.kassa3.model.entity.Category;
import com.example.kassa3.service.abstracts.CategoryService;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final  CategoryConverter categoryConverter;
    private final  CategoryService categoryService;
    private final  UserService userService;

    //    Все товары (потом добавить получение юзером только своих товаров)
    @GetMapping("/getAll")
    public List<CategoryDto> getAllCategory() {
        return categoryConverter.toDTOList(categoryService.findAll());
    }

    @GetMapping("/getAllActivate")
    public List<CategoryDto> getAllActivateCategory() {
        return categoryConverter.toDTOList(categoryService.findAllActivate());
    }

//    @GetMapping("/getAllDeleted")
//    public List<ItemDto> getAllDeletedItems() {
//        return itemConverter.toDTOList(itemService.findAllDeletedByUser());
//    }

    @GetMapping("/getAllDeactivate")
    public List<CategoryDto> getAllDeactivateCategory() {
        return categoryConverter.toDTOList(categoryService.findAllDeactivate());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (categoryDto.getId() != null) {
            return new ResponseEntity<>("id должно быть пустым", HttpStatus.BAD_REQUEST);
        }

        // check if user exists
        // (добавить:
        // 1) проверку юзера с принципалом
        // 2) проверку userConfirmed, если нет отправить токен на почту
        // 3) проверить activated, если нет то отправить сообщение о необходимости оплаты и ссылку на страницу оплаты

//        if (itemCreateDto.getUserId() == null) {
//            itemCreateDto.setUserId(userService.findByUsername(principal.getName()).getId());
//        } else if (!userService.existsById(itemCreateDto.getUserId())) {
//            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
//        }

        if (!userService.existsById(categoryDto.getUserId())) {
            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
        }

        // save user to the database
        categoryService.persist(categoryConverter.toModel(categoryDto));

        return new ResponseEntity<>("Category успешно сохранен", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (categoryDto.getId() == null) {
            return new ResponseEntity<>("id не должно быть пустым", HttpStatus.BAD_REQUEST);
        }

        if (!categoryService.existsById(categoryDto.getId())) {
            return new ResponseEntity<>("id не верный", HttpStatus.BAD_REQUEST);
        }

        // check if user exists
        // (добавить:
        // 1) проверку юзера с принципалом
        // 2) проверку userConfirmed, если нет отправить токен на почту
        // 3) проверить activated, если нет то отправить сообщение о необходимости оплаты и ссылку на страницу оплаты

//        if (itemCreateDto.getUserId() == null) {
//            itemCreateDto.setUserId(userService.findByUsername(principal.getName()).getId());
//        } else if (!userService.existsById(itemCreateDto.getUserId())) {
//            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
//        }

        if (!userService.existsById(categoryDto.getUserId())) {
            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
        }

        // save user to the database
        categoryService.update(categoryConverter.toModel(categoryDto));

        return new ResponseEntity<>("Category успешно обновлен", HttpStatus.OK);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateCategory(@PathVariable Long id) {
        if (!categoryService.existsById(id)) {
            return new ResponseEntity<>("Неверный id категории", HttpStatus.BAD_REQUEST);
        }
        Category category = categoryService.findById(id);
        category.setActivate(false);
        categoryService.update(category);
        return new ResponseEntity<>("Category успешно помечен неактивным", HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateCategory(@PathVariable Long id) {
        if (!categoryService.existsById(id)) {
            return new ResponseEntity<>("Неверный id категории", HttpStatus.BAD_REQUEST);
        }
        Category category = categoryService.findById(id);
        category.setActivate(true);
        categoryService.update(category);
        return new ResponseEntity<>("Category успешно восстановлен", HttpStatus.OK);
    }

}
