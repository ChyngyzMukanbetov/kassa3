package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ItemService extends ReadWriteService<Item, Long> {

    Page<Item> getAll (Pageable pageable);

    List<Item> findAllDeletedByUser(User user);

    List<Item> findAllDeactivate();

    List<Item> findAllActivate();

    List<Item> findActivateItemsByItemName(String itemName);
}
