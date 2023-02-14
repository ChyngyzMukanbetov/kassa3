package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.dto.documentDto.ItemSellDocDto;
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
    public ItemSellDoc resolve(ItemSellDocDto dto, @TargetType Class<ItemSellDoc> type) {
        ItemSellDoc itemSellDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemSellDoc = new ItemSellDoc();
        } else {
            itemSellDoc = itemSellDocService.findById(dto.getId());
        }
        return itemSellDoc;
    }
}
