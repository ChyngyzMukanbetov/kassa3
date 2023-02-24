package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ShopDto {
    private Long id;
    private String shopName;
    private List<ItemResponseDto> itemResponseDtoList;
    private Long userId;
    private AddressDto address;
    private BalanceDto balance;
}
