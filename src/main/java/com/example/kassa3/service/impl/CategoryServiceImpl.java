package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.CategoryDao;
import com.example.kassa3.model.entity.Category;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.service.abstracts.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl extends ReadWriteServiceImpl<Category, Long> implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Transactional
    @Override
    public List<Category> findAllDeactivate() {
        return categoryDao.findAllDeactivate();
    }

    @Transactional
    @Override
    public List<Category> findAllActivate() {
        return categoryDao.findAllActivate();
    }
}
