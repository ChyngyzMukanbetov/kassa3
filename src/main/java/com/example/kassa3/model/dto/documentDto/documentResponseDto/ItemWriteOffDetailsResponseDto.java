package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemWriteOffDetailsResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private BigDecimal basePrice;
    private BigDecimal count;
    private BigDecimal totalSum;
    private Long itemId;
    private Long itemWriteOffDocId;
}
