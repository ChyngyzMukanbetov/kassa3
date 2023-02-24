package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalPaymentDocCreateDto {
    @NotNull(message = "sum id required")
    @Min(value = 0, message = "sum can not be negative")
    private BigDecimal sum;

    @NotNull(message = "paymentDate id required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @PastOrPresent(message = "Дата оплаты не может быть в будущем")
    private LocalDate paymentDate;

    @NotNull(message = "isNonCash field is required and must be either true or false")
    private boolean nonCash;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @Min(value = 1)
    private Long additionalCreditDocId;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
