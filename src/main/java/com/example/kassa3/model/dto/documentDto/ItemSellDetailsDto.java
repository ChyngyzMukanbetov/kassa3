package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemSellDetailsDto {
    private Long id;

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    @Min(value = 0, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    @NotNull
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "The count must be positive")
    private BigDecimal count;

    @NotNull
    @Min(value = 0, message = "The sum must be positive")
    private BigDecimal sum;

    @Min(value = 0, message = "The discountSum must be positive")
    private BigDecimal discountSum;

    @NotNull
    @Min(value = 0, message = "The totalSum must be positive")
    private BigDecimal totalSum;

    @NotNull(message = "isNonCash field is required and must be either true or false")
    private Boolean nonCash;

    @NotNull(message = "isOnDebt field is required and must be either true or false")
    private Boolean onDebt;

    @Min(value = 0L, message = "The sumOfDebt must be positive")
    private BigDecimal sumOfDebt;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemSellDocId;
}
