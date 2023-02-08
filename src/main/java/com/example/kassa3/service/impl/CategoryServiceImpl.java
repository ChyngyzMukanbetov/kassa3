package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.CategoryDao;
import com.example.kassa3.model.entity.Category;
import com.example.kassa3.service.abstracts.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ReadWriteServiceImpl<Category, Long> implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }
}
