package com.example.kassa3.model.dto.documentDto;

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
public class ItemArrivalDetailsDto {

    private Long id;

    private boolean activate;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal basePrice;

    boolean useBasePriceWAM;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal price;

    boolean usePriceWAM;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    @ToString.Exclude
    private BigDecimal count;

    private BigDecimal sum;

    private boolean isNonCash;
    private boolean isOnCredit;

    private BigDecimal sumOfCredit;

    @NotNull(message = "itemId should not be null")
    private Long itemId;

    private ItemArrivalDocDto itemArrivalDoc;
}




