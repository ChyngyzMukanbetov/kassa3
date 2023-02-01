package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.PartnerDao;
import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.service.abstracts.PartnerService;
import org.springframework.stereotype.Service;

@Service
public class PartnerServiceImpl extends ReadWriteServiceImpl<Partner, Long> implements PartnerService {
    public PartnerServiceImpl(PartnerDao partnerDao) { super(partnerDao);}
}
