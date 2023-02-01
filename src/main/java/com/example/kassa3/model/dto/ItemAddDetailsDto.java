package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemAddDetailsDto {

    private Long id;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal basePrice;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal price;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    @ToString.Exclude
    private Integer count;

    @NotNull(message = "itemId should not be null")
    private Long itemId;
}
