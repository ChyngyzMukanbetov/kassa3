package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemArrivalDetailsCreateDto {

    @NotNull(message = "basePrice is required")
    @Min(value = 0, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    private boolean useBasePriceWAM;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    private boolean usePriceWAM;

    @NotNull(message = "count is required")
    @Min(value = 0, message = "The count must be positive")
    @ToString.Exclude
    private BigDecimal count;

    @NotNull(message = "sum is required")
    @Min(value = 0, message = "The sum must be positive")
    @ToString.Exclude
    private BigDecimal sum;

    private boolean nonCash;

    @NotNull(message = "onCredit should be true or false and can not be null")
    private boolean onCredit;

    @Min(value = 0, message = "The sumOfCredit must be positive")
    @ToString.Exclude
    private BigDecimal sumOfCredit;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemArrivalDocId; //игнорим при toModel
}




