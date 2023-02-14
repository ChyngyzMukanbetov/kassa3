package com.example.kassa3.service.impl.document;

import com.example.kassa3.dao.abstracts.document.ItemArrivalDocDao;
import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.document.ItemArrivalDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.math.RoundingMode.HALF_UP;

@Service
public class ItemArrivalDocServiceImpl extends ReadWriteServiceImpl<ItemArrivalDoc, Long> implements ItemArrivalDocService {

    private final ItemArrivalDocDao itemArrivalDocDao;
    private final ItemService itemService;

    public ItemArrivalDocServiceImpl(ItemArrivalDocDao itemArrivalDocDao, ItemService itemService) {
        super(itemArrivalDocDao);
        this.itemArrivalDocDao = itemArrivalDocDao;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public ItemArrivalDoc persist(ItemArrivalDoc itemArrivalDoc) {

        //update items(count, basePrice, price) from ItemArrivalDetailsList
        for (ItemArrivalDetails itemArrivalDetails : itemArrivalDoc.getItemArrivalDetailsList()) {

            if (itemArrivalDetails.isActivate()) {
                //set itemBasePrice
                if (itemArrivalDetails.isUseBasePriceWAM()) {
                    itemArrivalDetails.getItem().setBasePrice(
                            ((itemArrivalDetails.getItem().getBasePrice().multiply(itemArrivalDetails.getItem().getCount())).
                                    add(itemArrivalDetails.getBasePrice().multiply(itemArrivalDetails.getCount()))).
                                    divide(itemArrivalDetails.getItem().getCount().add(itemArrivalDetails.getCount()),2, HALF_UP));
                } else {
                    itemArrivalDetails.getItem().setBasePrice(itemArrivalDetails.getBasePrice());
                }

                //set  itemPrice
                if (itemArrivalDetails.isUsePriceWAM()) {
                    itemArrivalDetails.getItem().setPrice(
                            ((itemArrivalDetails.getItem().getPrice().multiply(itemArrivalDetails.getItem().getCount())).
                                    add(itemArrivalDetails.getPrice().multiply(itemArrivalDetails.getCount()))).
                                    divide(itemArrivalDetails.getItem().getCount().add(itemArrivalDetails.getCount()),2, HALF_UP));
                } else {
                    itemArrivalDetails.getItem().setPrice(itemArrivalDetails.getPrice());
                }

                //set itemCount
                itemArrivalDetails.getItem().setCount(itemArrivalDetails.getItem().getCount().add(itemArrivalDetails.getCount()));

                //update item
                itemService.update(itemArrivalDetails.getItem());
            }
        }
        itemArrivalDocDao.persist(itemArrivalDoc);
        return itemArrivalDoc;
    }
}