package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.ItemReturnToProviderDocDao;
import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.document.ItemReturnToProviderDetails;
import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.document.ItemReturnToProviderDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ItemReturnToProviderDocServiceImpl extends ReadWriteServiceImpl<ItemReturnToProviderDoc, Long> implements ItemReturnToProviderDocService {

    private final ItemReturnToProviderDocDao itemReturnToProviderDocDao;
    private final ItemService itemService;
    private final BalanceService balanceService;

    public ItemReturnToProviderDocServiceImpl(ItemReturnToProviderDocDao itemReturnToProviderDocDao, ItemService itemService, BalanceService balanceService) {
        super(itemReturnToProviderDocDao);
        this.itemReturnToProviderDocDao = itemReturnToProviderDocDao;
        this.itemService = itemService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public ItemReturnToProviderDoc persist(ItemReturnToProviderDoc itemReturnToProviderDoc) {

        if (!itemReturnToProviderDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1311", "Попытка сохранить(persist) неактивный(activate = false) itemReturnToProviderDoc в Service");
        }

        Balance balance = balanceService.findByShop(itemReturnToProviderDoc.getShop());

        //update items(count) from ItemSellDetailsList
        for (ItemReturnToProviderDetails itemReturnToProviderDetails : itemReturnToProviderDoc.getItemReturnToProviderDetailsList()) {

            if (itemReturnToProviderDetails.isActivate()) {
                //set itemCount
                itemReturnToProviderDetails.getItem().setCount(itemReturnToProviderDetails.getItem().getCount().subtract(itemReturnToProviderDetails.getCount()));

                //update item
                itemService.update(itemReturnToProviderDetails.getItem());

                if (itemReturnToProviderDetails.isNonCash()) {
                    balance.setNonCashSum(balance.getNonCashSum().add(itemReturnToProviderDetails.getSum()));
                } else {
                    balance.setCashSum(balance.getCashSum().add(itemReturnToProviderDetails.getSum()));
                }
            }
        }
        balanceService.update(balance);

        itemReturnToProviderDocDao.persist(itemReturnToProviderDoc);
        return itemReturnToProviderDoc;
    }

    @Override
    @Transactional
    public ItemReturnToProviderDoc deactivate(ItemReturnToProviderDoc itemReturnToProviderDoc) {
        Balance balance = balanceService.findByShop(itemReturnToProviderDoc.getShop());

        //update items(count, basePrice, price) from ItemReturnToProviderDetailsList
        for (ItemReturnToProviderDetails itemReturnToProviderDetails : itemReturnToProviderDoc.getItemReturnToProviderDetailsList()) {

            if (itemReturnToProviderDetails.isActivate()) {
                //set itemCount
                itemReturnToProviderDetails.getItem().setCount(itemReturnToProviderDetails.getItem().getCount().subtract(itemReturnToProviderDetails.getCount()));

                //update item
                itemService.update(itemReturnToProviderDetails.getItem());


                if (itemReturnToProviderDetails.isNonCash()) {
                    balance.setNonCashSum(balance.getNonCashSum().add(itemReturnToProviderDetails.getSum()));
                } else {
                    balance.setCashSum(balance.getCashSum().add(itemReturnToProviderDetails.getSum()));
                }

                itemReturnToProviderDetails.setActivate(false);
                itemReturnToProviderDetails.setDeactivateDate(LocalDate.now());
            }
        }
        balanceService.update(balance);
        itemReturnToProviderDoc.setActivate(false);
        itemReturnToProviderDoc.setDeactivateDate(LocalDate.now());
        itemReturnToProviderDocDao.update(itemReturnToProviderDoc);
        return itemReturnToProviderDoc;
    }
}
