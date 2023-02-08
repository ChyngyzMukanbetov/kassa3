package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemSellDetailsDto {
    private Long id;

    private boolean activate;

    private BigDecimal basePrice;

    private BigDecimal price;

    private BigDecimal count;

    private BigDecimal sum;

    private BigDecimal discountSum;

    private BigDecimal totalSum;

    private boolean isNonCash;
    private boolean isOnDebt;

    private BigDecimal sumOfDebt;

    private ItemDto item;

    private ItemSellDocDto itemSellDoc;
}
