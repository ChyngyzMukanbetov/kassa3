package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemArrivalDetailsResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private BigDecimal basePrice;
    private boolean useBasePriceWAM;
    private BigDecimal price;
    private boolean usePriceWAM;
    private BigDecimal count;
    private BigDecimal sum;
    private boolean nonCash;
    private boolean onCredit;
    private BigDecimal sumOfCredit;
    private Long itemId;
    private Long itemArrivalDocId;
}
