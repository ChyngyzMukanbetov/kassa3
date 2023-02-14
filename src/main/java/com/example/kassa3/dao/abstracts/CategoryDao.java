package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Category;

import java.util.List;

public interface CategoryDao extends ReadWriteDao<Category, Long> {

    List<Category> findAllDeactivate();

    List<Category> findAllActivate();
}
