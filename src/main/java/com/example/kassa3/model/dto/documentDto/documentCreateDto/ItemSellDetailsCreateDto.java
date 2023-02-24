package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSellDetailsCreateDto {

    @NotNull(message = "basePrice is required")
    @Min(value = 0, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    @NotNull(message = "count is required")
    @Min(value = 0, message = "The count must be positive")
    private BigDecimal count;

    @NotNull(message = "sum is required")
    @Min(value = 0, message = "The sum must be positive")
    private BigDecimal sum;

    @Min(value = 0, message = "The discountSum must be positive")
    private BigDecimal discountSum;

    @NotNull(message = "totalSum is required")
    @Min(value = 0, message = "The totalSum must be positive")
    private BigDecimal totalSum;

    private boolean nonCash;

    @NotNull(message = "onDebit field is required and must be either true or false")
    private boolean onDebit;

    @Min(value = 0, message = "The sumOfDebit must be positive")
    private BigDecimal sumOfDebit;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemSellDocId; //игнорим при toModel
}
