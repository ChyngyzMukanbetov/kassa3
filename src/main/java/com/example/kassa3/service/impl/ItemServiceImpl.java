package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.ImageDao;
import com.example.kassa3.dao.abstracts.ItemDao;
import com.example.kassa3.model.entity.Image;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.service.abstracts.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private final ItemDao itemDao;
    private final ImageDao imageDao;

    public ItemServiceImpl(ItemDao itemDao, ImageDao imageDao) {
        super(itemDao);
        this.itemDao = itemDao;
        this.imageDao = imageDao;
    }

    @Transactional
    @Override
    public List<Item> getItemList() {
        return itemDao.getItemList();
    }

    public Page<Item> getAll (Pageable pageable){
        return itemDao.getAll(pageable);
    }

    @Override
    @Transactional
    public List<Item> getItemsByShopId(Long shopId) {
        List<Item> resultList = itemDao.getItemsByShopId(shopId);
        List<Long> itemIdList = resultList.stream().map(Item::getId).collect(Collectors.toList());
        Map<Long, List<Image>> mapImage = itemIdList.stream()
                .collect(Collectors.toMap(Function.identity(), imageDao::getImagesByItemId));
//        Map<Long, List<Review>> mapReview = itemIdList.stream()
//                .collect(Collectors.toMap(Function.identity(), reviewDao::getReviewsByItemId));
        resultList.forEach(item -> item.setImages(mapImage.get(item.getId())));
//        resultList.forEach(item -> item.setReviews(mapReview.get(item.getId())));
        return resultList;
    }

    public List<Item> getItemsByShop(Shop shop) {
        return itemDao.findAll().stream().filter(item -> item.getShop().equals(shop)).collect(Collectors.toList());
    }

}
