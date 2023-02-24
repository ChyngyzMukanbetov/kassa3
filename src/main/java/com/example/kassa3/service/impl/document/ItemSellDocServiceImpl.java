package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.ItemSellDocDao;
import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.document.ItemSellDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ItemSellDocServiceImpl extends ReadWriteServiceImpl<ItemSellDoc, Long> implements ItemSellDocService {
    private final ItemSellDocDao itemSellDocDao;
    private final ItemService itemService;
    private final BalanceService balanceService;

    public ItemSellDocServiceImpl(ItemSellDocDao itemSellDocDao, ItemService itemService, BalanceService balanceService) {
        super(itemSellDocDao);
        this.itemSellDocDao = itemSellDocDao;
        this.itemService = itemService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public ItemSellDoc persist(ItemSellDoc itemSellDoc) {

        if (!itemSellDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1304", "Попытка сохранить(persist) неактивный(activate = false) itemSellDoc в Service");
        }

        Balance balance = balanceService.findByShop(itemSellDoc.getShop());

        //update items(count) from ItemSellDetailsList
        for (ItemSellDetails itemSellDetails : itemSellDoc.getItemSellDetailsList()) {

            if (itemSellDetails.isActivate()) {
                //set itemCount
                itemSellDetails.getItem().setCount(itemSellDetails.getItem().getCount().subtract(itemSellDetails.getCount()));

                //update item
                itemService.update(itemSellDetails.getItem());

                if (itemSellDetails.getSumOfDebit() == null) {
                    if (itemSellDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().add(itemSellDetails.getTotalSum()));
                    } else {
                        balance.setCashSum(balance.getCashSum().add(itemSellDetails.getTotalSum()));
                    }
                } else {
                    balance.setDebitSum(balance.getDebitSum().add(itemSellDetails.getSumOfDebit()));
                    if (itemSellDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().add(itemSellDetails.getTotalSum().subtract(itemSellDetails.getSumOfDebit())));
                    } else {
                        balance.setCashSum(balance.getCashSum().add(itemSellDetails.getTotalSum().subtract(itemSellDetails.getSumOfDebit())));
                    }
                }
            }
        }
        balanceService.update(balance);

        if (itemSellDoc.getDebitDoc() != null) {
            itemSellDoc.getDebitDoc().setDebitDate(itemSellDoc.getItemSellDate());
        }
        itemSellDocDao.persist(itemSellDoc);
        return itemSellDoc;
    }

    @Override
    @Transactional
    public ItemSellDoc deactivate(ItemSellDoc itemSellDoc) {
        Balance balance = balanceService.findByShop(itemSellDoc.getShop());

        //update items(count, basePrice, price) from ItemSellDetailsList
        for (ItemSellDetails itemSellDetails : itemSellDoc.getItemSellDetailsList()) {

            if (itemSellDetails.isActivate()) {
                //set itemCount
                itemSellDetails.getItem().setCount(itemSellDetails.getItem().getCount().add(itemSellDetails.getCount()));

                //update item
                itemService.update(itemSellDetails.getItem());

                if (itemSellDetails.getSumOfDebit() == null) {
                    if (itemSellDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().subtract(itemSellDetails.getSum()));
                    } else {
                        balance.setCashSum(balance.getCashSum().subtract(itemSellDetails.getSum()));
                    }
                } else {
                    if (!itemSellDoc.getDebitDoc().isWrittenOff()) {
                        balance.setDebitSum(balance.getDebitSum().subtract(itemSellDetails.getSumOfDebit()));
                    }
                    if (itemSellDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().subtract(itemSellDetails.getSum().subtract(itemSellDetails.getSumOfDebit())));
                    } else {
                        balance.setCashSum(balance.getCashSum().subtract(itemSellDetails.getSum().subtract(itemSellDetails.getSumOfDebit())));
                    }
                }
                itemSellDetails.setActivate(false);
                itemSellDetails.setDeactivateDate(LocalDate.now());
            }
        }
        balanceService.update(balance);

        if (itemSellDoc.getDebitDoc() != null) {
            itemSellDoc.getDebitDoc().setActivate(false);
            itemSellDoc.getDebitDoc().setDeactivateDate(LocalDate.now());
        }
        itemSellDoc.setActivate(false);
        itemSellDoc.setDeactivateDate(LocalDate.now());
        itemSellDocDao.update(itemSellDoc);
        return itemSellDoc;
    }
}
