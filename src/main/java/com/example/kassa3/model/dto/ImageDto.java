package com.example.kassa3.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ImageDto {

    private Long id;
    private byte[] picture;

    // Метод для вывода картинок на главной странице с применением Thymeleaf
    public String getBase64String() {
        return new String(Base64.getEncoder().encode(picture));
    }
}
