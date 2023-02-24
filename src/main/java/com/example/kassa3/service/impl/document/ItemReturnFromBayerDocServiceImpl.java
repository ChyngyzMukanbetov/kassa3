package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.ItemReturnFromBayerDocDao;
import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.document.ItemReturnFromBayerDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class ItemReturnFromBayerDocServiceImpl extends ReadWriteServiceImpl<ItemReturnFromBayerDoc, Long> implements ItemReturnFromBayerDocService {

    private final ItemReturnFromBayerDocDao itemReturnFromBayerDocDao;
    private final ItemService itemService;
    private final BalanceService balanceService;

    public ItemReturnFromBayerDocServiceImpl(ItemReturnFromBayerDocDao itemReturnFromBayerDocDao, ItemService itemService, BalanceService balanceService) {
        super(itemReturnFromBayerDocDao);
        this.itemReturnFromBayerDocDao = itemReturnFromBayerDocDao;
        this.itemService = itemService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public ItemReturnFromBayerDoc persist(ItemReturnFromBayerDoc itemReturnFromBayerDoc) {

        if (!itemReturnFromBayerDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1310", "Попытка сохранить(persist) неактивный(activate = false) itemReturnFromBayerDoc в Service");
        }

        Balance balance = balanceService.findByShop(itemReturnFromBayerDoc.getShop());
        //update items(count) from ItemReturnFromBayerDetailsList
        for (ItemReturnFromBayerDetails itemReturnFromBayerDetails : itemReturnFromBayerDoc.getItemReturnFromBayerDetailsList()) {

            if (itemReturnFromBayerDetails.isActivate()) {
                //set itemCount
                itemReturnFromBayerDetails.getItem().setCount(itemReturnFromBayerDetails.getItem().getCount().add(itemReturnFromBayerDetails.getCount()));
                //update item
                itemService.update(itemReturnFromBayerDetails.getItem());
            }
            if (itemReturnFromBayerDetails.isNonCash()) {
                balance.setNonCashSum(balance.getNonCashSum().subtract(itemReturnFromBayerDetails.getSum()));
            } else {
                balance.setCashSum(balance.getCashSum().subtract(itemReturnFromBayerDetails.getSum()));
            }
        }
        balanceService.update(balance);
        itemReturnFromBayerDocDao.persist(itemReturnFromBayerDoc);
        return itemReturnFromBayerDoc;
    }

    @Override
    @Transactional
    public ItemReturnFromBayerDoc deactivate(ItemReturnFromBayerDoc itemReturnFromBayerDoc) {
        Balance balance = balanceService.findByShop(itemReturnFromBayerDoc.getShop());

        //update items(count, basePrice, price) from ItemReturnFromBayerDetailsList
        for (ItemReturnFromBayerDetails itemReturnFromBayerDetails : itemReturnFromBayerDoc.getItemReturnFromBayerDetailsList()) {

            if (itemReturnFromBayerDetails.isActivate()) {
                //set itemCount
                itemReturnFromBayerDetails.getItem().setCount(itemReturnFromBayerDetails.getItem().getCount().add(itemReturnFromBayerDetails.getCount()));

                //update item
                itemService.update(itemReturnFromBayerDetails.getItem());


                if (itemReturnFromBayerDetails.isNonCash()) {
                    balance.setNonCashSum(balance.getNonCashSum().subtract(itemReturnFromBayerDetails.getSum()));
                } else {
                    balance.setCashSum(balance.getCashSum().subtract(itemReturnFromBayerDetails.getSum()));
                }

                itemReturnFromBayerDetails.setActivate(false);
                itemReturnFromBayerDetails.setDeactivateDate(LocalDate.now());
            }
        }
        balanceService.update(balance);
        itemReturnFromBayerDoc.setActivate(false);
        itemReturnFromBayerDoc.setDeactivateDate(LocalDate.now());
        itemReturnFromBayerDocDao.update(itemReturnFromBayerDoc);
        return itemReturnFromBayerDoc;
    }
}
