package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.PartnerDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private boolean activate;

    private LocalDate documentData;

    private LocalDate itemSellData;

    private String comment;

    private List<ItemSellDetailsDto> itemSellDetailsList;

    private PartnerDto partner;

    private DebitDocDto debitDoc;

    private Long shopId;

    private Long userId;
}
