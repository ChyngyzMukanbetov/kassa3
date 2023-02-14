package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.dto.documentDto.ItemSellDetailsDto;
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
    public ItemSellDetails resolve(ItemSellDetailsDto dto, @TargetType Class<ItemSellDetails> type) {
        ItemSellDetails itemSellDetails;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemSellDetails = new ItemSellDetails();
        } else {
            itemSellDetails = itemSellDetailsService.findById(dto.getId());
        }
        return itemSellDetails;
    }
}
