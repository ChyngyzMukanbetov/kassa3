package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.CityDao;
import com.example.kassa3.model.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl extends ReadWriteDaoImpl<City, Long> implements CityDao {
}
