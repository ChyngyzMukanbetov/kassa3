package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.ItemWriteOffDocDao;
import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ItemWriteOffDocServiceImpl extends ReadWriteServiceImpl<ItemWriteOffDoc, Long> implements ItemWriteOffDocService {

    private final ItemWriteOffDocDao itemWriteOffDocDao;
    private final ItemService itemService;

    public ItemWriteOffDocServiceImpl(ItemWriteOffDocDao itemWriteOffDocDao, ItemService itemService) {
        super(itemWriteOffDocDao);
        this.itemWriteOffDocDao = itemWriteOffDocDao;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public ItemWriteOffDoc persist(ItemWriteOffDoc itemWriteOffDoc) {

        if (!itemWriteOffDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1305", "Попытка сохранить(persist) неактивный(activate = false) itemWriteOffDoc в Service");
        }

        //update items(count) from ItemSellDetailsList
        for (ItemWriteOffDetails itemWriteOffDetails : itemWriteOffDoc.getItemWriteOffDetailsList()) {

            if (itemWriteOffDetails.isActivate()) {
                //set itemCount
                itemWriteOffDetails.getItem().setCount(itemWriteOffDetails.getItem().getCount().subtract(itemWriteOffDetails.getCount()));

                //update item
                itemService.update(itemWriteOffDetails.getItem());
            }
        }
        itemWriteOffDocDao.persist(itemWriteOffDoc);
        return itemWriteOffDoc;
    }

    @Override
    @Transactional
    public ItemWriteOffDoc deactivate(ItemWriteOffDoc itemWriteOffDoc) {

        //update items(count, basePrice, price) from ItemWriteOffDetailsList
        for (ItemWriteOffDetails itemWriteOffDetails : itemWriteOffDoc.getItemWriteOffDetailsList()) {

            if (itemWriteOffDetails.isActivate()) {
                //set itemCount
                itemWriteOffDetails.getItem().setCount(itemWriteOffDetails.getItem().getCount().add(itemWriteOffDetails.getCount()));

                //update item
                itemService.update(itemWriteOffDetails.getItem());

                itemWriteOffDetails.setActivate(false);
                itemWriteOffDetails.setDeactivateDate(LocalDate.now());
            }
        }

        itemWriteOffDoc.setActivate(false);
        itemWriteOffDoc.setDeactivateDate(LocalDate.now());
        itemWriteOffDocDao.update(itemWriteOffDoc);
        return itemWriteOffDoc;
    }
}
