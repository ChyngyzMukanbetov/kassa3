package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ItemAddDetailsDao;
import com.example.kassa3.model.entity.ItemAddDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemAddDetailsDaoImpl extends ReadWriteDaoImpl<ItemAddDetails, Long> implements ItemAddDetailsDao {
}
