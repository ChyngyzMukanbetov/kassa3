package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.AddressDto;
import com.example.kassa3.model.entity.Address;
import com.example.kassa3.service.abstracts.AddressService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressResolver {

    public final AddressService addressService;

    @ObjectFactory
    public Address resolve(AddressDto dto, @TargetType Class<Address> type) {
        Address address;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            address = new Address();
        } else {
            address = addressService.findById(dto.getId());
        }
        return address;
    }
}
