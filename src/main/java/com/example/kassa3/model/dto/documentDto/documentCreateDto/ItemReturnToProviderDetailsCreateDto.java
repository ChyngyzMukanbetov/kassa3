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
public class ItemReturnToProviderDetailsCreateDto {

    @NotNull(message = "price is required")
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    @NotNull(message = "count is required")
    @Min(value = 0, message = "The count must be positive")
    private BigDecimal count;

    @NotNull(message = "sum is required")
    @Min(value = 0, message = "The sum must be positive")
    private BigDecimal sum;

    private boolean nonCash;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemReturnToProviderDocId; //игнорим при toModel
}
