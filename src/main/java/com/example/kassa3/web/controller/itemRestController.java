package com.example.kassa3.web.controller;

import com.example.kassa3.converter.ItemConverter;
import com.example.kassa3.dao.abstracts.ItemDao;
import com.example.kassa3.model.dto.ItemDto;
import com.example.kassa3.model.entity.Item;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
public class itemRestController {

    private ItemDao itemDao;
    private ItemConverter itemConverter;

    //    Все товары
    @GetMapping("/getAll")
    public List<ItemDto> getAllItems() {
        List<Item> itemList = itemDao.findAll();
        List<ItemDto> itemDtoList = itemConverter.toDTOList(itemList);
        return itemDtoList;
    }
}

