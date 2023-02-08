package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.ShopDto;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.service.abstracts.ShopService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShopResolver {

    private final ShopService shopService;

    @ObjectFactory
    public Shop resolve(Long id, @TargetType Class<Shop> type) {
        Shop shop;
        if (id == null) {
            return null;
        } else {
            shop = shopService.findById(id);
        }
        return shop;
    }

    @ObjectFactory
    public Shop resolve(ShopDto dto, @TargetType Class<Shop> type) {
        Shop shop;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            shop = new Shop();
        } else {
            shop = shopService.findById(dto.getId());
        }
        return shop;
    }
}
