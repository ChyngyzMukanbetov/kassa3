package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.PhoneDto;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.enums.PhoneCode;
import com.example.kassa3.service.abstracts.PhoneService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PhoneResolver {
    private final PhoneService phoneService;

    @ObjectFactory
    public Phone resolve(PhoneDto dto, @TargetType Class<Item> type) {
        Phone phone;
        if (dto == null) {
            return null;
        } else if (phoneService.findByCodeAndNumber(PhoneCode.getPhoneCodeByCode(dto.getPhoneCodeCode()).get(), dto.getPhoneNumber()) == null) {
            phone = new Phone();
        } else {
            phone = phoneService.findByCodeAndNumber(PhoneCode.getPhoneCodeByCode(dto.getPhoneCodeCode()).get(), dto.getPhoneNumber());
        }
        return phone;
    }


//    @ObjectFactory
//    public Phone resolve(PhoneDto dto, @TargetType Class<Item> type) {
//        Phone phone;
//        if (dto == null) {
//            return null;
//        } else if (dto.getId() == null) {
//            phone = new Phone();
//        } else {
//            phone = phoneService.findById(dto.getId());
//        }
//        return phone;
//    }
}
