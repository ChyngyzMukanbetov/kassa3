package com.example.kassa3.model.dto.documentDto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemArrivalDetailsDto {

    private Long id;

    @NotNull
    @Min(value = 0, message = "The basePrice must be positive")
    private BigDecimal basePrice;

    private Boolean useBasePriceWAM;

    @NotNull
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    private Boolean usePriceWAM;

    @NotNull
    @Min(value = 0, message = "The count must be positive")
    @ToString.Exclude
    private BigDecimal count;

    @NotNull
    @Min(value = 0, message = "The sum must be positive")
    @ToString.Exclude
    private BigDecimal sum;

    private Boolean nonCash;

    @NotNull(message = "onCredit should be true or false and can not be null")
    private Boolean onCredit;

    @Min(value = 0, message = "The sumOfCredit must be positive")
    @ToString.Exclude
    private BigDecimal sumOfCredit;

    @NotNull(message = "itemId should not be null")
    @Min(value = 1)
    private Long itemId;

    @Min(value = 1)
    private Long itemArrivalDocId; //игнорим при toModel
}




