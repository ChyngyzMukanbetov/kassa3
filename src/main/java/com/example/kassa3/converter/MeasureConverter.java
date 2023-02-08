package com.example.kassa3.converter;

import com.example.kassa3.model.enums.Measure;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MeasureConverter {
    default Measure fromName(String name) {
        return Measure.getMeasureByName(name).orElse(null);
    }

    default String rusNameFromColor(Measure measure) {
        return measure != null ? measure.getRusName() : null;
    }
}
