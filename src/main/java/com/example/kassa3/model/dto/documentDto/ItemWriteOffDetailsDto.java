package com.example.kassa3.model.dto.documentDto;

import com.example.kassa3.model.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemWriteOffDetailsDto {
    private Long id;

    private boolean activate;

    private BigDecimal basePrice;

    private BigDecimal count;

    private BigDecimal totalSum;

    private ItemDto item;

    private ItemWriteOffDocDto itemWriteOffDoc;
}
