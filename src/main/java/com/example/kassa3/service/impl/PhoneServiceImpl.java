package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.PhoneDao;
import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.enums.PhoneCode;
import com.example.kassa3.service.abstracts.PhoneService;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl extends ReadWriteServiceImpl<Phone, Long> implements PhoneService {

    private final PhoneDao phoneDao;


    public PhoneServiceImpl(PhoneDao phoneDao) {
        super(phoneDao);
        this.phoneDao = phoneDao;
    }

    @Override
    public Phone findByCodeAndNumber(PhoneCode phoneCode, String number) {
        return phoneDao.findByCodeAndNumber(phoneCode, number);
    }
}
