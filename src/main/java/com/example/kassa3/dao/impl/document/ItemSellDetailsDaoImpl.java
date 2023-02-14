package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemSellDetailsDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemSellDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemSellDetailsDaoImpl extends ReadWriteDaoImpl<ItemSellDetails, Long> implements ItemSellDetailsDao {
}
