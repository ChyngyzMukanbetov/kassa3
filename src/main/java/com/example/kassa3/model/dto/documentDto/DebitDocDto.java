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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class DebitDocDto {
    private Long id;

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    private Boolean returned;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate documentData;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Past(message = "Дата выдачи в долг не может быть в будущем")
    private LocalDate debitData;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate returnDate;

    @NotBlank
    @Min(value = 0, message = "sumOfDebt can not be negative")
    private BigDecimal sumOfDebt;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @NotNull
    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @Min(value = 1)
    private Long itemSellDocId;

    @Valid
    private List<IncomeDocDto> incomeDocList;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
