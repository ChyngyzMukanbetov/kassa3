package com.example.kassa3.model.dto;

import com.example.kassa3.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class PartnerDto {
    private Long id;

    boolean isProvider;
    boolean isBuyer;

    @NotBlank(message = "name is required")
    @Size(min=4, max=30, message = "name should be min 4 max 30 letters")
    private String name;

    @NotBlank(message = "phone is required")
    @Size(min=6, max=15, message = "phone should be min 6 max 15 letters")
    private String phone;

    @Size(max=30, message = "description should be max 30 letters")
    private String description;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}