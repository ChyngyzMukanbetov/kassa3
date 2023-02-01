package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.ItemAddDetailsDto;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.ItemAddDetails;
import com.example.kassa3.service.abstracts.ItemAddDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemAddDetailsResolver {
    private final ItemAddDetailsService itemAddDetailsService;

    @ObjectFactory
    public ItemAddDetails resolve(ItemAddDetailsDto dto, @TargetType Class<Item> type) {
        ItemAddDetails itemAddDetails;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemAddDetails = new ItemAddDetails();
        } else {
            itemAddDetails = itemAddDetailsService.findById(dto.getId());
        }
        return itemAddDetails;
    }
}
