package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemWriteOffDetailsCreateDto {

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    @Min(value = 0L, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    @NotNull
    @Min(value = 0L, message = "The count must be positive")
    private BigDecimal count;

    @Min(value = 0L, message = "The totalSum must be positive")
    private BigDecimal sum;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemWriteOffDocId;
}
