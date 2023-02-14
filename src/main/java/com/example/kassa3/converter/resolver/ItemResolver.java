package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.ItemDto;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.service.abstracts.ItemService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemResolver {

    public final ItemService itemService;

    @ObjectFactory
    public Item resolve(Long id, @TargetType Class<Item> type) {
        Item item;
        if (id == null) {
            return null;
        } else {
            item = itemService.findById(id);
        }
        return item;
    }

    @ObjectFactory
    public Item resolve(ItemDto dto, @TargetType Class<Item> type) {
        Item item;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            item = new Item();
        } else {
            item = itemService.findById(dto.getId());
        }
        return item;
    }
}
