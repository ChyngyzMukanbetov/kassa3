package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorizationDocResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private LocalDateTime documentDateTime;
    private LocalDate inventorizationDate;
    private String comment;
    private boolean itemInventorizationActivate;
    private List<InventorizationDetailsResponseDto> inventorizationDetailsList;
    private boolean balanceInventorizationActivate;
    private BigDecimal cashSumOld;
    private BigDecimal nonCashSumOld;
    private BigDecimal cashSumNew;
    private BigDecimal nonCashSumNew;
    private BigDecimal cashSumDifference;
    private BigDecimal nonCashSumDifference;
    private Long shopId;
    private Long userId;
}
