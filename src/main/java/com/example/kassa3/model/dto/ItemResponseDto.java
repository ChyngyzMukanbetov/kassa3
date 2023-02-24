package com.example.kassa3.model.dto;

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
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private String description;
    private String colorName;
    private String measureName;
    private String size;
    private Integer barCodeNumber;
    private String barCodeType;
    private BigDecimal basePrice;
    private BigDecimal price;
    private BigDecimal count;
    private Long categoryId;
    private String categoryName;
    private Long madeInCountryId;
    private String madeInCountryName;
    private Long userId;
    private String userName;
    private Long shopId;
    private String shopName;
    private boolean activate;
    private ImageDto image;
}