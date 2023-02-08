package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.CityDto;
import com.example.kassa3.model.entity.City;
import com.example.kassa3.service.abstracts.CityService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CityResolver {

    public final CityService cityService;

    @ObjectFactory
    public City resolve(CityDto dto, @TargetType Class<City> type) {
        City city;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            city = new City();
        } else {
            city = cityService.findById(dto.getId());
        }
        return city;
    }
}
