package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDocDto;
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
    public ItemArrivalDoc resolve(ItemArrivalDocDto dto, @TargetType Class<ItemArrivalDoc> type) {
        ItemArrivalDoc itemArrivalDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemArrivalDoc = new ItemArrivalDoc();
        } else {
            itemArrivalDoc = itemArrivalDocService.findById(dto.getId());
        }
        return itemArrivalDoc;
    }
}
