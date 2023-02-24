package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDocCreateDto;
import com.example.kassa3.service.abstracts.document.ItemReturnToProviderDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemReturnToProviderDocResolver {
    private final ItemReturnToProviderDocService itemReturnToProviderDocService;

    @ObjectFactory
    public ItemReturnToProviderDoc resolve(Long id, @TargetType Class<ItemReturnToProviderDoc> type) {
        ItemReturnToProviderDoc itemReturnToProviderDoc;
        if (id == null) {
            return null;
        } else {
            itemReturnToProviderDoc = itemReturnToProviderDocService.findById(id);
        }
        return itemReturnToProviderDoc;
    }

    @ObjectFactory
    public ItemReturnToProviderDoc resolve(ItemReturnToProviderDocCreateDto dto, @TargetType Class<ItemReturnToProviderDoc> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemReturnToProviderDoc();
        }
    }
}
