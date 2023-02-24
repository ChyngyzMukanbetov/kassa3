package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDetailsCreateDto;
import com.example.kassa3.service.abstracts.document.ItemSellDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemSellDetailsResolver {
    private final ItemSellDetailsService itemSellDetailsService;

    @ObjectFactory
    public ItemSellDetails resolve(Long id, @TargetType Class<ItemSellDetails> type) {
        ItemSellDetails itemSellDetails;
        if (id == null) {
            return null;
        } else {
            itemSellDetails = itemSellDetailsService.findById(id);
        }
        return itemSellDetails;
    }

    @ObjectFactory
    public ItemSellDetails resolve(ItemSellDetailsCreateDto dto, @TargetType Class<ItemSellDetails> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemSellDetails();
        }
    }
}
