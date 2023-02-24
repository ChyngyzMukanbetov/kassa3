package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalDebitDocCreateDto {
    @NotNull(message = "returnPlanDate is required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate returnPlanDate;

    @NotNull(message = "debitDate is required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate debitDate;

    @NotNull(message = "sumOfDebit is required")
    @Min(value = 0, message = "sumOfDebt can not be negative")
    private BigDecimal sumOfDebit;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @NotNull(message = "partnerId is required")
    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @Valid
    private List<AdditionalIncomeDocCreateDto> additionalIncomeDocList;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
