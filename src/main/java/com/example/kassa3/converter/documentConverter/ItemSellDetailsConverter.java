package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.ItemConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemSellDetailsResolver;
import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.dto.documentDto.ItemSellDetailsDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemSellDetailsResolver.class, ItemConverter.class})
public interface ItemSellDetailsConverter {
    ItemSellDetails toModel(Long itemSellDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemSellDoc")
    ItemSellDetails toModel(ItemSellDetailsDto itemSellDetailsDto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemSellDocId", source = "itemSellDoc.id")
    ItemSellDetailsDto toDTO(ItemSellDetails itemSellDetails);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemSellDoc")
    List<ItemSellDetails> toModelList(List<ItemSellDetailsDto> itemSellDetailsDtoList);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemSellDocId", source = "itemSellDoc.id")
    List<ItemSellDetailsDto> toDTOList(List<ItemSellDetails> itemSellDetailsList);
}
