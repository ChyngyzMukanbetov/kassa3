package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.ImageDao;
import com.example.kassa3.model.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDaoImpl extends ReadWriteDaoImpl<Image, Long> implements ImageDao {
    //   Поиск всех картинок для товара
    @Override
    public List<Image> getImagesByItemId(Long itemId) {
        return em.createQuery(
                        "SELECT NEW com.amr.project.model.entity.Image(" +
                                "i.id, i.picture)" +
                                "FROM Item s JOIN s.images i WHERE s.id = :itemId", Image.class)
                .setParameter("itemId", itemId).getResultList();
    }
}
