package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorizationDocCreateDto {
    @NotNull(message = "inventorizationDate id required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @PastOrPresent(message = "Дата оприходования не может быть в будущем")
    private LocalDate inventorizationDate;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @NotNull(message = "itemInventorizationActivate id required")
    private boolean itemInventorizationActivate;

    @Valid
    private List<InventorizationDetailsCreateDto> inventorizationDetailsList;

    @NotNull(message = "balanceInventorizationActivate id required")
    private boolean balanceInventorizationActivate;

    private BigDecimal cashSumOld;
    private BigDecimal nonCashSumOld;

    private BigDecimal cashSumNew;
    private BigDecimal nonCashSumNew;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
