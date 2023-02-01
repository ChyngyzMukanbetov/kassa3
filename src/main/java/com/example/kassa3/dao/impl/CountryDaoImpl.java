package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.CountryDao;
import com.example.kassa3.model.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl extends ReadWriteDaoImpl<Country, Long> implements CountryDao {
}
