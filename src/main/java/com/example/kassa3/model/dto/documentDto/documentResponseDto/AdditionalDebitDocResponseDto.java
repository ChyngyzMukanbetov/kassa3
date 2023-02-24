package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import com.example.kassa3.model.dto.PartnerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalDebitDocResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private boolean returned;
    private boolean WrittenOff;
    private LocalDate WriteOffDate;
    private String WriteOffReason;
    private LocalDateTime documentDateTime;
    private LocalDate debitDate;
    private LocalDate returnPlanDate;
    private LocalDate returnFactDate;
    private BigDecimal sumOfDebit;
    private String comment;
    private PartnerDto partner;
    private List<AdditionalIncomeDocResponseDto> additionalIncomeDocList;
    private Long shopId;
    private Long userId;
}
