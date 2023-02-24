package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorizationDetailsCreateDto {

    @NotNull(message = "count is required")
    @Min(value = 0, message = "The count must be positive")
    @ToString.Exclude
    private BigDecimal countOld;

    @NotNull(message = "count is required")
    @Min(value = 0, message = "The count must be positive")
    @ToString.Exclude
    private BigDecimal countNew;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long inventorizationDocId; //игнорим при toModel
}
