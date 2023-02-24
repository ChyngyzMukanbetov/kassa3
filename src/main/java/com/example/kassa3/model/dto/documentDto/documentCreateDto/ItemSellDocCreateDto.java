package com.example.kassa3.model.dto.documentDto.documentCreateDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSellDocCreateDto {

    @NotNull(message = "itemSellDate id required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @PastOrPresent(message = "Дата продажи не может быть в будущем")
    private LocalDate itemSellDate;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @Valid
    @NotNull(message = "itemSellDetailsList id required")
    private List<ItemSellDetailsCreateDto> itemSellDetailsList;

    @Valid
    private DebitDocCreateDto debitDoc;

    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
