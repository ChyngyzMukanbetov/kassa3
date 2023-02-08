package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.PartnerDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class PaymentDocDto {
    private Long id;

    private boolean activate;

    private BigDecimal sum;

    private LocalDate documentData;

    private LocalDate paymentData;

    private boolean isNonCash;

    private PartnerDto partner;

    private String comment;

    private CreditDocDto creditDoc;

    private Long shopId;

    private Long userId;
}
