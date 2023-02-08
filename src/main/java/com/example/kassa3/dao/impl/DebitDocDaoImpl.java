package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.DebitDocDao;
import com.example.kassa3.model.document.DebitDoc;
import org.springframework.stereotype.Repository;

@Repository
public class DebitDocDaoImpl extends ReadWriteDaoImpl<DebitDoc, Long> implements DebitDocDao {
}
