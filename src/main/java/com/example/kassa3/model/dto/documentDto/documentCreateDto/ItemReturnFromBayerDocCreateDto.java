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
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemReturnFromBayerDocCreateDto {

    @NotNull(message = "itemReturnFromBayerDate id required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @PastOrPresent(message = "Дата оприходования не может быть в будущем")
    private LocalDate itemReturnFromBayerDate;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @Valid
    @NotNull(message = "itemArrivalDetailsList id required")
    private List<ItemReturnFromBayerDetailsCreateDto> itemReturnFromBayerDetailsList;

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
