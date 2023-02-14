package com.example.kassa3.model.dto;

import com.example.kassa3.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class UserCreateDto {
    private Long id;
    @Email(message = "incorrect email format")
    @NotBlank(message = "email is required")
    @Size(max=50, message = "email should be max 50 letters")
    private String email;
    @NotBlank(message = "username is required")
    @Size(min=4, max=30, message = "username should be min 4 max 30 letters")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min=4, max=30, message = "password should be min 4 max 30 letters")
    private String password;
    @Valid
    @NotNull
    private PhoneDto phoneDto;
    @Size(max=30, message = "firstName should be max 30 letters")
    private String firstName;
    @Size(max=30, message = "lastName should be max 30 letters")
    private String lastName;
    @Size(max=10, message = "genderName should be max 10 letters")
    private String genderName;
    @Past(message = "can't be in the future")
    private LocalDate birthday;
}