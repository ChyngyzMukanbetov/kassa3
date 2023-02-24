package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class BalanceDto {
    private Long id;
    private BigDecimal cashSum;
    private BigDecimal nonCashSum;
    private BigDecimal creditSum;
    private BigDecimal debitSum;
    private Long shopId;
    private String shopName;
}
