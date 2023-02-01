package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.CategoryDao;
import com.example.kassa3.model.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends ReadWriteDaoImpl<Category, Long> implements CategoryDao {
}
