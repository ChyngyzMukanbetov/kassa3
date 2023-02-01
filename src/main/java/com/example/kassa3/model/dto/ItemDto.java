package com.example.kassa3.model.dto;

import com.example.kassa3.model.entity.Category;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal basePrice;
    private BigDecimal price;
    private int count;
    private Long categoryId;
    private Long userId;
    private Long shopId;
}

