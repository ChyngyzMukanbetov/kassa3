package com.example.kassa3.model.dto;

import com.example.kassa3.util.validation.enumValidation.ColorNameValidation;
import com.example.kassa3.util.validation.enumValidation.MeasureNameValidation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemCreateDto {
    private Long id;
    @NotBlank(message = "itemName is required")
    @Size(min=4, max=30, message = "itemName should be min 4 max 30 letters")
    private String itemName;
    @Size(max=200, message = "description should be max 200 letters")
    private String description;

    private Long categoryId;
    @ColorNameValidation
    private String colorName;
    @MeasureNameValidation
    private String measureName;
    @Size(max=10, message = "size should be max 10 letters")
    private String size;

    private ImageDto image;

    private Long madeInCountryId;

    @Min(value = 0)
    private Integer barCodeNumber;
    @Size(max=48, message = "barCodeNumber should be max 48 letters")
    private String barCodeType;
    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;
    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
