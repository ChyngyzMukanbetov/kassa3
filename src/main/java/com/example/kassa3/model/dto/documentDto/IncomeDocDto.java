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
import javax.validation.constraints.*;
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
    @Past(message = "Дата выручки не может быть в будущем")
    private LocalDate incomeData;

    @NotNull(message = "isNonCash field is required and must be either true or false")
    private Boolean nonCash;

    @NotNull
    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    @Min(value = 1)
    private Long debitDocId;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
