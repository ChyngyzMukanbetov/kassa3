package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Partner;

import java.util.List;

public interface PartnerService extends ReadWriteService<Partner, Long> {
    List<Partner> findAllActivate();
    List<Partner> findAllDeactivate();
}
