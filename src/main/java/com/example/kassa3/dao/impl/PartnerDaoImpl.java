package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.PartnerDao;
import com.example.kassa3.model.entity.Partner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartnerDaoImpl extends ReadWriteDaoImpl<Partner, Long> implements PartnerDao {

    @Override
    public List<Partner> findAllDeactivate() {
        return em.createQuery("select p from Partner p where p.activate=false", Partner.class).getResultList();
    }

    @Override
    public List<Partner> findAllActivate() {
        return em.createQuery("select p from Partner p where p.activate=true", Partner.class).getResultList();
    }
}
