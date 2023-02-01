package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Image;

import java.util.List;

public interface ImageDao extends ReadWriteDao<Image, Long> {
    List<Image> getImagesByItemId(Long itemId);
}
