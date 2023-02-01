package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.PartnerDao;
import com.example.kassa3.model.entity.Partner;
import org.springframework.stereotype.Repository;

@Repository
public class PartnerDaoImpl extends ReadWriteDaoImpl<Partner, Long> implements PartnerDao {
}
