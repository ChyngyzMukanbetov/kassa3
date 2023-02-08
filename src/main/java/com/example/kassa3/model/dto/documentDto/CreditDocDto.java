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
public class CreditDocDto {

    private Long id;

    @Pattern(regexp = "^(true|false)$", message = "activate field allowed input: true or false")
    private boolean activate;

    @Pattern(regexp = "^(true|false)$", message = "isReturned field allowed input: true or false")
    private boolean isReturned;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate documentData;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate creditData;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate returnDate;

    @NotBlank
    @Min(value = 0, message = "sumOfCredit can not be negative")
    private BigDecimal sumOfCredit;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @NotBlank
    @Valid
    private PartnerDto partner;
    @Valid
    private ItemArrivalDocDto itemArrivalDoc;
    @Valid
    private List<PaymentDocDto> paymentDocList;
    @NotBlank
    private Long shopID;
    @NotBlank
    private Long userId;
}

