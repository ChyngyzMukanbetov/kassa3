package com.example.kassa3.converter;

import com.example.kassa3.model.enums.Gender;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GenderConverter {

    default Gender fromName(String name) {
        return Gender.getGenderByName(name).orElse(null);
    }

    default String rusNameFromGender(Gender gender) {
        return gender != null ? gender.getRusName() : null;
    }
}
