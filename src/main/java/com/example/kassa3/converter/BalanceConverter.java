package com.example.kassa3.converter;

import com.example.kassa3.model.dto.BalanceDto;
import com.example.kassa3.model.entity.Balance;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface BalanceConverter {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopName")
    BalanceDto toDTO(Balance balance);

    List<BalanceDto> toDTOList(List<Balance> balanceList);

}
