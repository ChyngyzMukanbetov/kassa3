package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.CountryResolver;
import com.example.kassa3.model.entity.Country;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = {CountryResolver.class})
public interface CountryConverter {
    Country toModel(Long countryId);
}
