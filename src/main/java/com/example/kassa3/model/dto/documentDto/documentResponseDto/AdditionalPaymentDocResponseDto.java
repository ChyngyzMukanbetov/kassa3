package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import com.example.kassa3.model.dto.PartnerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalPaymentDocResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private BigDecimal sum;
    private LocalDateTime documentDateTime;
    private LocalDate paymentDate;
    private Boolean nonCash;
    private PartnerDto partner;
    private String comment;
    private Long AdditionalCreditDocId;
    private Long shopId;
    private Long userId;
}
