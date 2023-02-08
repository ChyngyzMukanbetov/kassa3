package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.AddressDao;
import com.example.kassa3.model.entity.Address;
import com.example.kassa3.service.abstracts.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ReadWriteServiceImpl<Address, Long> implements AddressService {

    private final AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        super(addressDao);
        this.addressDao = addressDao;
    }
}
