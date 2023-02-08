package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.CityDao;
import com.example.kassa3.model.entity.City;
import com.example.kassa3.service.abstracts.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ReadWriteServiceImpl<City, Long> implements CityService {

    private final CityDao countryDao;

    public CityServiceImpl(CityDao countryDao) {
        super(countryDao);
        this.countryDao = countryDao;
    }
}
