package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDocCreateDto;
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
    public ItemWriteOffDoc resolve(Long id, @TargetType Class<ItemWriteOffDoc> type) {
        ItemWriteOffDoc itemWriteOffDoc;
        if (id == null) {
            return null;
        } else {
            itemWriteOffDoc = itemWriteOffDocService.findById(id);
        }
        return itemWriteOffDoc;
    }

    @ObjectFactory
    public ItemWriteOffDoc resolve(ItemWriteOffDocCreateDto dto, @TargetType Class<ItemWriteOffDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemWriteOffDoc();
        }
    }
}
