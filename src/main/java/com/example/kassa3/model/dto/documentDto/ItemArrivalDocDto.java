package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.PartnerDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemArrivalDocDto {

    private Long id;
    private boolean activate;
    private LocalDate documentData;
    private LocalDate itemArrivalData;
    private String comment;
    private List<ItemArrivalDetailsDto> itemAddDetailsListDto;
    private CreditDocDto creditDoc;
    private PartnerDto partnerDto;
    private Long shopId;
    private Long userId;
}



