package com.example.kassa3.converter;

import com.example.kassa3.model.enums.Color;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ColorConverter {

    default Color fromName(String name) {
        return Color.getColorByName(name).orElse(null);
    }

    default String rusNameFromColor(Color color) {
        return color != null ? color.getRusName() : null;
    }
}
