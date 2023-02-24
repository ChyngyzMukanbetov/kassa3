package com.example.kassa3.web.controller.userAccessController;

import com.example.kassa3.converter.AddressConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.model.dto.AddressDto;
import com.example.kassa3.model.dto.ShopDto;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.service.abstracts.CityService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/shops")
public class ShopRestConverter {

    private final ShopService shopService;
    private final ShopConverter shopConverter;
    private final UserService userService;
    private final CityService cityService;
    private final AddressConverter addressConverter;


    @GetMapping("/getShopsByUser")
    public List<ShopDto> getAllShopsByUser(Principal principal) {
        return shopConverter.toDTOList(shopService.findAllActivateByUser(userService.findByUsername(principal.getName())));
    }

    @PutMapping("/setAddress/{shopId}")
    public ResponseEntity<String> setAddressToShop(@PathVariable Long shopId, @Valid @RequestBody AddressDto addressDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (!shopService.existsById(shopId)) {
            return new ResponseEntity<>("Shop с таким id не существует", HttpStatus.CONFLICT);
        }

        if (!cityService.existsById(addressDto.getCityId())) {
            return new ResponseEntity<>("City с таким id не существует", HttpStatus.CONFLICT);
        }

        Shop shop = shopService.findById(shopId);
        shop.setAddress(addressConverter.toModel(addressDto));
        shopService.update(shop);
        return new ResponseEntity<>("Shop.address успешно обновлен", HttpStatus.OK);
    }
}
