package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class AddressDto {
    private Long id;
    @Size(min=4, max=50, message = "street should be min 4 max 50 letters")
    private String street;
    @Size(min=1, max=30, message = "street should be min 1 max 30 letters")
    private String house;
    @NotNull(message = "cityId id required")
    @Min(value = 1)
    private Long cityId;
    private String CityName;
    private String cityIndex;
    private String countryName;
}
