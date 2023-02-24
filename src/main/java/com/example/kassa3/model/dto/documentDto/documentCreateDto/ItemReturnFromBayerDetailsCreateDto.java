package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemReturnFromBayerDetailsCreateDto {

    @NotNull(message = "basePrice is required")
    @Min(value = 0, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    @NotNull(message = "count is required")
    @Min(value = 0, message = "The count must be positive")
    @ToString.Exclude
    private BigDecimal count;

    @NotNull(message = "sum is required")
    @Min(value = 0, message = "The sum must be positive")
    @ToString.Exclude
    private BigDecimal sum;

    private boolean nonCash;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemReturnFromBayerDocId; //игнорим при toModel
}
