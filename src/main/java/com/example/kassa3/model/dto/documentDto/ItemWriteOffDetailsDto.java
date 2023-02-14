package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ItemWriteOffDetailsDto {
    private Long id;

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    @Min(value = 0L, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    @NotNull
    @Min(value = 0L, message = "The count must be positive")
    private BigDecimal count;

    @Min(value = 0L, message = "The totalSum must be positive")
    private BigDecimal totalSum;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemWriteOffDocId;
}
