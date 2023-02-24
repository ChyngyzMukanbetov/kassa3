package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSellDetailsResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private BigDecimal basePrice;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal sum;
    private BigDecimal discountSum;
    private BigDecimal totalSum;
    private boolean nonCash;
    private boolean onDebit;
    private BigDecimal sumOfDebit;
    private Long itemId;
    private Long itemSellDocId;
}
