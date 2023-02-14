package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.PartnerDao;
import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.service.abstracts.PartnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartnerServiceImpl extends ReadWriteServiceImpl<Partner, Long> implements PartnerService {

    private final PartnerDao partnerDao;

    public PartnerServiceImpl(PartnerDao partnerDao) {
        super(partnerDao);
        this.partnerDao = partnerDao;
    }

    @Transactional
    @Override
    public List<Partner> findAllDeactivate() {
        return partnerDao.findAllDeactivate();
    }

    @Transactional
    @Override
    public List<Partner> findAllActivate() {
        return partnerDao.findAllActivate();
    }
}
