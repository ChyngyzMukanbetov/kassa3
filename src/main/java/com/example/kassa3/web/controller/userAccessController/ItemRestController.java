package com.example.kassa3.web.controller.userAccessController;

import com.example.kassa3.converter.ItemResponseConverter;
import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.model.dto.ItemCreateDto;
import com.example.kassa3.model.dto.ItemResponseDto;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.service.abstracts.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/items")
public class ItemRestController {

    private final  ItemService itemService;
    private final ItemResponseConverter itemResponseConverter;
    private final ItemCreateConverter itemCreateConverter;
    private final UserService userService;
    private final ShopService shopService;
    private final CategoryService categoryService;
    private final CountryService countryService;

    //    Все товары (потом добавить получение юзером только своих товаров)
    @GetMapping("/getAll")
    public List<ItemResponseDto> getAllItems() {
        return itemResponseConverter.toDTOList(itemService.findAll());
    }

    @GetMapping("/getAllActivate")
    public List<ItemResponseDto> getAllActivateItems() {
        return itemResponseConverter.toDTOList(itemService.findAllActivate());
    }

//    @GetMapping("/getAllDeleted")
//    public List<ItemDto> getAllDeletedItems() {
//        return itemConverter.toDTOList(itemService.findAllDeletedByUser());
//    }

    @GetMapping("/getAllDeactivate")
    public List<ItemResponseDto> getAllDeactivateItems() {
        return itemResponseConverter.toDTOList(itemService.findAllDeactivate());
    }

    @GetMapping("/getActivateItemsByName/{itemName}")
    public List<ItemResponseDto> getActivateItemsByName(@PathVariable String itemName) {
        return itemResponseConverter.toDTOList(itemService.findActivateItemsByItemName(itemName));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createItem(@Valid @RequestBody ItemCreateDto itemCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (itemCreateDto.getId() != null) {
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

        if (!userService.existsById(itemCreateDto.getUserId())) {
            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
        }

        if (!shopService.existsById(itemCreateDto.getShopId())) {
            return new ResponseEntity<>("Shop не существует", HttpStatus.CONFLICT);
        }

        if (itemCreateDto.getCategoryId() != null) {
            if (!categoryService.existsById(itemCreateDto.getCategoryId())) {
                return new ResponseEntity<>("Category не существует", HttpStatus.CONFLICT);
            } else if (!categoryService.findById(itemCreateDto.getCategoryId()).getUser().getId().equals(itemCreateDto.getUserId())) {
                return new ResponseEntity<>("указана Category другого пользователя", HttpStatus.CONFLICT);
            }
        }

        if (itemCreateDto.getMadeInCountryId() != null) {
            if (!countryService.existsById(itemCreateDto.getMadeInCountryId())) {
                return new ResponseEntity<>("Country не существует", HttpStatus.CONFLICT);
            }
        }

        // save user to the database
        itemService.persist(itemCreateConverter.toModel(itemCreateDto));

        return new ResponseEntity<>("Item успешно сохранен", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@Valid @RequestBody ItemCreateDto itemCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (!itemService.existsById(itemCreateDto.getId())) {
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

        if (!userService.existsById(itemCreateDto.getUserId())) {
            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
        }

        if (!shopService.existsById(itemCreateDto.getShopId())) {
            return new ResponseEntity<>("Shop не существует", HttpStatus.CONFLICT);
        }

        if (itemCreateDto.getCategoryId() != null) {
            if (!categoryService.existsById(itemCreateDto.getCategoryId())) {
                return new ResponseEntity<>("Category не существует", HttpStatus.CONFLICT);
            } else if (!categoryService.findById(itemCreateDto.getCategoryId()).getUser().getId().equals(itemCreateDto.getUserId())) {
                return new ResponseEntity<>("указана Category другого пользователя", HttpStatus.CONFLICT);
            }
        }

        if (itemCreateDto.getMadeInCountryId() != null) {
            if (!countryService.existsById(itemCreateDto.getMadeInCountryId())) {
                return new ResponseEntity<>("Country не существует", HttpStatus.CONFLICT);
            }
        }

        // save user to the database
        itemService.update(itemCreateConverter.toModel(itemCreateDto));

        return new ResponseEntity<>("Item успешно обновлен", HttpStatus.OK);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateItem(@PathVariable Long id) {
        if (!itemService.existsById(id)) {
            return new ResponseEntity<>("Неверный id товара", HttpStatus.BAD_REQUEST);
        }
        Item item = itemService.findById(id);
        item.setActivate(false);
        itemService.update(item);
        return new ResponseEntity<>("Item успешно помечен неактивным", HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateItem(@PathVariable Long id) {
        if (!itemService.existsById(id)) {
            return new ResponseEntity<>("Неверный id товара", HttpStatus.BAD_REQUEST);
        }
        Item item = itemService.findById(id);
        item.setActivate(true);
        itemService.update(item);
        return new ResponseEntity<>("Item успешно восстановлен", HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
//        if (!itemService.existsById(id)) {
//            return new ResponseEntity<>("Неверный id товара", HttpStatus.BAD_REQUEST);
//        }
//        itemService.delete(itemService.findById(id));
//        return new ResponseEntity<>("Item успешно удален", HttpStatus.OK);
//    }

}