package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.PartnerResolver;
import com.example.kassa3.model.dto.ItemDto;
import com.example.kassa3.model.entity.Item;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = {PartnerResolver.class, })
public interface PartnerConverter {

    @Mapping(source = "user.id", target = "userId")
    ItemDto toDTO(Item item);

    @Mapping(target = "user.id", source = "userId")
    Item toModel(ItemDto itemDto);
}
