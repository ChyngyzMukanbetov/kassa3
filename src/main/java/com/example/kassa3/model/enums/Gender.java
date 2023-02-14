package com.example.kassa3.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("Male", "Мужчина", "М"),
    FEMALE("Female", "Женщина", "Ж");

    private final String engName;
    private final String rusName;
    private final String shortRusName;

    public String getEngName() {
        return engName;
    }

    public String getRusName() {
        return rusName;
    }

    public String getShortRusName() {
        return shortRusName;
    }

    // Reverse lookup methods
    public static Optional<Gender> getGenderByName(String name) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.engName.equals(name)
                        || gender.rusName.equals(name)
                        || gender.shortRusName.equals(name))
                .findFirst();
    }
}
