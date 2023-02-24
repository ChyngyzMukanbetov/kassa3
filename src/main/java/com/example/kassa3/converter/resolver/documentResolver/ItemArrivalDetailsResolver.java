package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDetailsCreateDto;
import com.example.kassa3.service.abstracts.document.ItemArrivalDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemArrivalDetailsResolver {
    private final ItemArrivalDetailsService itemArrivalDetailsService;

    @ObjectFactory
    public ItemArrivalDetails resolve(Long id, @TargetType Class<ItemArrivalDetails> type) {
        ItemArrivalDetails itemArrivalDetails;
        if (id == null) {
            return null;
        } else {
            itemArrivalDetails = itemArrivalDetailsService.findById(id);
        }
        return itemArrivalDetails;
    }

    @ObjectFactory
    public ItemArrivalDetails resolve(ItemArrivalDetailsCreateDto dto, @TargetType Class<ItemArrivalDetails> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemArrivalDetails();
        }
    }
}
