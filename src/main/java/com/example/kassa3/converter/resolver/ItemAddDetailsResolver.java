package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDetailsDto;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.service.abstracts.ItemArrivalDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemAddDetailsResolver {
    private final ItemArrivalDetailsService itemArrivalDetailsService;

    @ObjectFactory
    public ItemArrivalDetails resolve(ItemArrivalDetailsDto dto, @TargetType Class<Item> type) {
        ItemArrivalDetails itemArrivalDetails;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemArrivalDetails = new ItemArrivalDetails();
        } else {
            itemArrivalDetails = itemArrivalDetailsService.findById(dto.getId());
        }
        return itemArrivalDetails;
    }
}
