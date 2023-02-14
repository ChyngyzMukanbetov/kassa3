package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemArrivalDetailsDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemArrivalDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemArrivalDetailsDaoImpl extends ReadWriteDaoImpl<ItemArrivalDetails, Long> implements ItemArrivalDetailsDao {
}
