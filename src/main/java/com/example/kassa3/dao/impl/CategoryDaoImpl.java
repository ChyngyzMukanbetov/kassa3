package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.CategoryDao;
import com.example.kassa3.model.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl extends ReadWriteDaoImpl<Category, Long> implements CategoryDao {

    @Override
    public List<Category> findAllDeactivate() {
        return em.createQuery("select c from Category c where c.activate=false", Category.class).getResultList();
    }

    @Override
    public List<Category> findAllActivate() {
        return em.createQuery("select c from Category c where c.activate=true", Category.class).getResultList();
    }
}
