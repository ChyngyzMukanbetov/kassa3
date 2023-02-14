package com.example.kassa3.model.dto;

import com.example.kassa3.util.validation.enumValidation.PhoneCodeCodeValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
    @PhoneCodeCodeValidation
    private String phoneCodeCode;
    @Size(min=6, max=6, message = "phoneNumber should be 6 digits")
    private String phoneNumber;
}
