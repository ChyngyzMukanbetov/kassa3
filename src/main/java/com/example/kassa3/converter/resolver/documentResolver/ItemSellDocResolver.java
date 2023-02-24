package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDocCreateDto;
import com.example.kassa3.service.abstracts.document.ItemSellDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemSellDocResolver {
    private final ItemSellDocService itemSellDocService;

    @ObjectFactory
    public ItemSellDoc resolve(Long id, @TargetType Class<ItemSellDoc> type) {
        ItemSellDoc itemSellDoc;
        if (id == null) {
            return null;
        } else {
            itemSellDoc = itemSellDocService.findById(id);
        }
        return itemSellDoc;
    }

    @ObjectFactory
    public ItemSellDoc resolve(ItemSellDocCreateDto dto, @TargetType Class<ItemSellDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemSellDoc();
        }
    }
}
