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
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemSellDocDto {
    private Long id;

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate documentData;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Past(message = "Дата продажи не может быть в будущем")
    private LocalDate itemSellData;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @Valid
    private List<ItemSellDetailsDto> itemSellDetailsList;

    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @Valid
    private DebitDocDto debitDoc;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
