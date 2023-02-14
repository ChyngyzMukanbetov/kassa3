package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

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
public class CategoryDto {
    private Long id;
    @NotBlank(message = "name is required")
    @Size(min=4, max=30, message = "name should be min 4 max 30 letters")
    private String name;
    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
