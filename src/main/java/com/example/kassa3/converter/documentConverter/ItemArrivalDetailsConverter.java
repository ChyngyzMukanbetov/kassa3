package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.ItemConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemArrivalDetailsResolver;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDetailsDto;
import com.example.kassa3.model.document.ItemArrivalDetails;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemArrivalDetailsResolver.class, ItemConverter.class})
public interface ItemArrivalDetailsConverter {

    ItemArrivalDetails toModel(Long itemArrivalDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemArrivalDoc")
    @Mapping(target = "useBasePriceWAM", defaultValue = "true")
    @Mapping(target = "usePriceWAM", defaultValue = "false")
    @Mapping(target = "nonCash", defaultValue = "false")
    @Mapping(target = "onCredit", defaultValue = "false")
    ItemArrivalDetails toModel(ItemArrivalDetailsDto itemArrivalDetailsDto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemArrivalDocId", source = "itemArrivalDoc.id")
    ItemArrivalDetailsDto toDTO(ItemArrivalDetails itemArrivalDetails);

//    @Mapping(source = "itemId", target = "item")
//    @Mapping(ignore = true, target = "itemArrivalDoc")
    List<ItemArrivalDetails> toModelList(List<ItemArrivalDetailsDto> itemArrivalDetailsDtoList);

//    @Mapping(target = "itemId", source = "item.id")
//    @Mapping(target = "itemArrivalDocId", source = "itemArrivalDoc.id")
    List<ItemArrivalDetailsDto> toDTOList(List<ItemArrivalDetails> itemArrivalDetailsList);
}