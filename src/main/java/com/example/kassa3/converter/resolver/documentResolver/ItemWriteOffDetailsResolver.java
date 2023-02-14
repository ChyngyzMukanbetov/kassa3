package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.dto.documentDto.ItemWriteOffDetailsDto;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDetailsService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemWriteOffDetailsResolver {
    private final ItemWriteOffDetailsService itemWriteOffDetailsService;

    @ObjectFactory
    public ItemWriteOffDetails resolve(ItemWriteOffDetailsDto dto, @TargetType Class<ItemWriteOffDetails> type) {
        ItemWriteOffDetails itemWriteOffDetails;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            itemWriteOffDetails = new ItemWriteOffDetails();
        } else {
            itemWriteOffDetails = itemWriteOffDetailsService.findById(dto.getId());
        }
        return itemWriteOffDetails;
    }
}
