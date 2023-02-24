package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDocCreateDto;
import com.example.kassa3.service.abstracts.document.ItemReturnFromBayerDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemReturnFromBayerDocResolver {
    private final ItemReturnFromBayerDocService itemReturnFromBayerDocService;

    @ObjectFactory
    public ItemReturnFromBayerDoc resolve(Long id, @TargetType Class<ItemReturnFromBayerDoc> type) {
        ItemReturnFromBayerDoc itemReturnFromBayerDoc;
        if (id == null) {
            return null;
        } else {
            itemReturnFromBayerDoc = itemReturnFromBayerDocService.findById(id);
        }
        return itemReturnFromBayerDoc;
    }

    @ObjectFactory
    public ItemReturnFromBayerDoc resolve(ItemReturnFromBayerDocCreateDto dto, @TargetType Class<ItemReturnFromBayerDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemReturnFromBayerDoc();
        }
    }
}
