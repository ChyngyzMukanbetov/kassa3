package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Partner;

import java.util.List;

public interface PartnerDao extends ReadWriteDao<Partner, Long> {

    List<Partner> findAllDeactivate();

    List<Partner> findAllActivate();
}
