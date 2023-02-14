package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.dto.documentDto.ItemWriteOffDocDto;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemWriteOffDocResolver {
    private final ItemWriteOffDocService itemWriteOffDocService;

    @ObjectFactory
    public ItemWriteOffDoc resolve(ItemWriteOffDocDto dto, @TargetType Class<ItemWriteOffDoc> type) {
        ItemWriteOffDoc itemWriteOffDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemWriteOffDoc = new ItemWriteOffDoc();
        } else {
            itemWriteOffDoc = itemWriteOffDocService.findById(dto.getId());
        }
        return itemWriteOffDoc;
    }
}
