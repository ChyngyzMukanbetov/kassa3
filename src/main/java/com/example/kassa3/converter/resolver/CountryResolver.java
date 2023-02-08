package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.entity.Country;
import com.example.kassa3.service.abstracts.CountryService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CountryResolver {

    public final CountryService countryService;

    @ObjectFactory
    public Country resolve(Long countryId, @TargetType Class<Country> type) {
        if (countryId == null) {
            return null;
        } else if (!countryService.existsById(countryId)) {
            return null;
        } else {
            return countryService.findById(countryId);
        }
    }
}
