package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.CityResolver;
import com.example.kassa3.model.dto.CityDto;
import com.example.kassa3.model.entity.City;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = {CountryConverter.class, CityResolver.class})
public interface CityConverter {
    City toModel(Long cityId);

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.name", target = "countryName")
    CityDto toDTO(City city);

    @Mapping(target = "country", source = "countryId")
    City toModel(CityDto cityDto);

    List<City> toModelList(List<CityDto> cityDtoList);

    List<CityDto> toDTOList(List<City> cityList);
}