package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private boolean confirm;
    private PhoneDto phoneDto;
    private String firstName;
    private String lastName;
    private String genderName;
    private LocalDate birthday;
    private LocalDate userRegistrationDate;
    private LocalDate ActivateStartDate;
    private LocalDate ActivateExpiryDate;
}