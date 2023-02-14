package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Image;

import java.util.List;

public interface ImageService extends ReadWriteService<Image, Long> {
    List<Image> getImagesByItemId(Long itemId);
}
