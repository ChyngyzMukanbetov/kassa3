package com.example.kassa3.model.dto;

import com.example.kassa3.model.enums.PhoneCode;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
    private String phoneCodeCode;
    private String phoneNumber;
}
