package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ItemService extends ReadWriteService<Item, Long> {

    List<Item> getItemList();
    List<Item> getItemsByShop(Shop shop);

    Page<Item> getAll (Pageable pageable);

    List<Item> getItemsByShopId(Long shopId);
}
