package com.example.kassa3.converter;

import com.example.kassa3.model.enums.PhoneCode;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PhoneCodeConverter {

    default PhoneCode PhoneCodeFromString(String phoneCodeCode) {
        return PhoneCode.getPhoneCodeByCode(phoneCodeCode).get();
    }
}
