package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemReturnToProviderDetailsDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.ItemReturnToProviderDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemReturnToProviderDetailsDaoImpl extends ReadWriteDaoImpl<ItemReturnToProviderDetails, Long> implements ItemReturnToProviderDetailsDao {
}
