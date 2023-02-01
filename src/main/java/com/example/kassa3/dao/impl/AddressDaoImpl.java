package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.AddressDao;
import com.example.kassa3.model.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends ReadWriteDaoImpl<Address, Long> implements AddressDao {
}
