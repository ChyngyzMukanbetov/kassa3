package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDocCreateDto;
import com.example.kassa3.service.abstracts.document.ItemArrivalDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemArrivalDocResolver {
    private final ItemArrivalDocService itemArrivalDocService;

    @ObjectFactory
    public ItemArrivalDoc resolve(Long id, @TargetType Class<ItemArrivalDoc> type) {
        ItemArrivalDoc itemArrivalDoc;
        if (id == null) {
            return null;
        } else {
            itemArrivalDoc = itemArrivalDocService.findById(id);
        }
        return itemArrivalDoc;
    }

    @ObjectFactory
    public ItemArrivalDoc resolve(ItemArrivalDocCreateDto dto, @TargetType Class<ItemArrivalDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemArrivalDoc();
        }
    }
}
