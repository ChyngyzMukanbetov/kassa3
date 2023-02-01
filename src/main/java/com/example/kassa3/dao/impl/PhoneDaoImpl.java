package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.PhoneDao;
import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.enums.PhoneCode;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneDaoImpl extends ReadWriteDaoImpl<Phone, Long> implements PhoneDao {
    @Override
    public Phone findByCodeAndNumber(PhoneCode phoneCode, String number) {
        return em.createQuery("SELECT Phone FROM Phone p JOIN p.phoneCode WHERE p.phoneCode = :phoneCode and p.phoneNumber = :number", Phone.class)
                .setParameter("phoneCode", phoneCode).setParameter("number", number).getSingleResult();
    }
}