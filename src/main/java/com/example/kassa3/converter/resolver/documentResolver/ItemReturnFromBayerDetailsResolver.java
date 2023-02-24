package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDetailsCreateDto;
import com.example.kassa3.service.abstracts.document.ItemReturnFromBayerDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemReturnFromBayerDetailsResolver {
    private final ItemReturnFromBayerDetailsService itemReturnFromBayerDetailsService;

    @ObjectFactory
    public ItemReturnFromBayerDetails resolve(Long id, @TargetType Class<ItemReturnFromBayerDetails> type) {
        ItemReturnFromBayerDetails itemReturnFromBayerDetails;
        if (id == null) {
            return null;
        } else {
            itemReturnFromBayerDetails = itemReturnFromBayerDetailsService.findById(id);
        }
        return itemReturnFromBayerDetails;
    }

    @ObjectFactory
    public ItemReturnFromBayerDetails resolve(ItemReturnFromBayerDetailsCreateDto dto, @TargetType Class<ItemReturnFromBayerDetails> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemReturnFromBayerDetails();
        }
    }
}
