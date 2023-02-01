package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.enums.PhoneCode;

public interface PhoneDao extends ReadWriteDao<Phone, Long> {
    Phone findByCodeAndNumber(PhoneCode phoneCode, String number);
}
