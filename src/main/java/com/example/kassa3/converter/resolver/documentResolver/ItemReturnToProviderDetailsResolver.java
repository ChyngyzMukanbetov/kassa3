package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemReturnToProviderDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDetailsCreateDto;
import com.example.kassa3.service.abstracts.document.ItemReturnToProviderDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemReturnToProviderDetailsResolver {
    private final ItemReturnToProviderDetailsService itemReturnToProviderDetailsService;

    @ObjectFactory
    public ItemReturnToProviderDetails resolve(Long id, @TargetType Class<ItemReturnToProviderDetails> type) {
        ItemReturnToProviderDetails itemReturnToProviderDetails;
        if (id == null) {
            return null;
        } else {
            itemReturnToProviderDetails = itemReturnToProviderDetailsService.findById(id);
        }
        return itemReturnToProviderDetails;
    }

    @ObjectFactory
    public ItemReturnToProviderDetails resolve(ItemReturnToProviderDetailsCreateDto dto, @TargetType Class<ItemReturnToProviderDetails> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemReturnToProviderDetails();
        }
    }
}
