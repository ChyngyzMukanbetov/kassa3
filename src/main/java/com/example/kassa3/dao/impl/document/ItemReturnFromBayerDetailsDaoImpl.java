package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemReturnFromBayerDetailsDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemReturnFromBayerDetailsDaoImpl extends ReadWriteDaoImpl<ItemReturnFromBayerDetails, Long> implements ItemReturnFromBayerDetailsDao {
}
