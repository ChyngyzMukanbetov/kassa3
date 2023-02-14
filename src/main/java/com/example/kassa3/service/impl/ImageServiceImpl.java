package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ImageDao;
import com.example.kassa3.model.entity.Image;
import com.example.kassa3.service.abstracts.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl extends ReadWriteServiceImpl<Image, Long> implements ImageService {

    private final ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        super(imageDao);
        this.imageDao = imageDao;
    }

    @Override
    public List<Image> getImagesByItemId(Long itemId) {
        return imageDao.getImagesByItemId(itemId);
    }
}
