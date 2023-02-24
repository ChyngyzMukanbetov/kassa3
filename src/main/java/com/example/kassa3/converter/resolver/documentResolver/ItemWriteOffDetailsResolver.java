package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDetailsCreateDto;
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
    public ItemWriteOffDetails resolve(Long id, @TargetType Class<ItemWriteOffDetails> type) {
        ItemWriteOffDetails itemWriteOffDetails;
        if (id == null) {
            return null;
        } else {
            itemWriteOffDetails = itemWriteOffDetailsService.findById(id);
        }
        return itemWriteOffDetails;
    }

    @ObjectFactory
    public ItemWriteOffDetails resolve(ItemWriteOffDetailsCreateDto dto, @TargetType Class<ItemWriteOffDetails> type) {
        if (dto == null) {
            return null;
        } else {
            return new ItemWriteOffDetails();
        }
    }
}
