package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.CountryDao;
import com.example.kassa3.model.entity.Country;
import com.example.kassa3.service.abstracts.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends ReadWriteServiceImpl<Country, Long> implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        super(countryDao);
        this.countryDao = countryDao;
    }
}
