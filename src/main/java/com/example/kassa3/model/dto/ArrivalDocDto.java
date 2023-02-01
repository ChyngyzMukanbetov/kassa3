package com.example.kassa3.model.dto;

import com.example.kassa3.model.entity.ItemAddDetails;
import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ArrivalDocDto {

    private Long id;
    private Calendar documentData;
    private Calendar itemArrivalData;
    private List<ItemAddDetailsDto> itemAddDetailsListDto;
    private PartnerDto partnerDto;
    private boolean isOnDebt;
    private Long shopId;
    private Long userId;
}
