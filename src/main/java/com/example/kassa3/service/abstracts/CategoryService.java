package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Category;

import java.util.List;

public interface CategoryService extends ReadWriteService<Category, Long> {
    List<Category> findAllActivate();
    List<Category> findAllDeactivate();
}
