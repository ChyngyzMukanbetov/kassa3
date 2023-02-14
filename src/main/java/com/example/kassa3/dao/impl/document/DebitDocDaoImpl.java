package com.example.kassa3.dao.impl.document;

import com.example.kassa3.dao.abstracts.document.DebitDocDao;
import com.example.kassa3.dao.impl.ReadWriteDaoImpl;
import com.example.kassa3.model.document.DebitDoc;
import org.springframework.stereotype.Repository;

@Repository
public class DebitDocDaoImpl extends ReadWriteDaoImpl<DebitDoc, Long> implements DebitDocDao {
}
