package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.PartnerDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class IncomeDocDto {
    private Long id;

    @NotBlank
    @Min(value = 0, message = "sum can not be negative")
    private BigDecimal sum;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate documentData;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate incomeData;

    @Pattern(regexp = "^(true|false)$", message = "isNonCash field allowed input: true or false")
    private boolean isNonCash;

    @NotBlank
    @Valid
    private PartnerDto partner;

    @Pattern(regexp = "^(true|false)$", message = "activate field allowed input: true or false")
    private boolean activate;

    @Valid
    @NotBlank
    private DebitDocDto debitDoc;
    @NotBlank
    private Long shopId;
    @NotBlank
    private Long userId;
}
