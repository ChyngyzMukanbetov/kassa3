package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.enums.PhoneCode;

public interface PhoneService extends ReadWriteService<Phone, Long> {
    Phone findByCodeAndNumber(PhoneCode phoneCode, String number);
}
