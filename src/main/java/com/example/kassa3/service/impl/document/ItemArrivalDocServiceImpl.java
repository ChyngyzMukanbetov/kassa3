package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.ItemArrivalDocDao;
import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.document.ItemArrivalDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static java.math.RoundingMode.HALF_UP;

@Service
public class ItemArrivalDocServiceImpl extends ReadWriteServiceImpl<ItemArrivalDoc, Long> implements ItemArrivalDocService {

    private final ItemArrivalDocDao itemArrivalDocDao;
    private final ItemService itemService;
    private final BalanceService balanceService;

    public ItemArrivalDocServiceImpl(ItemArrivalDocDao itemArrivalDocDao, ItemService itemService, BalanceService balanceService) {
        super(itemArrivalDocDao);
        this.itemArrivalDocDao = itemArrivalDocDao;
        this.itemService = itemService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public ItemArrivalDoc persist(ItemArrivalDoc itemArrivalDoc) {

        if (!itemArrivalDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1303", "Попытка сохранить(persist) неактивный(activate = false) itemArrivalDoc в Service");
        }

        Balance balance = balanceService.findByShop(itemArrivalDoc.getShop());

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

                if (itemArrivalDetails.getSumOfCredit() == null) {
                    if (itemArrivalDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().subtract(itemArrivalDetails.getSum()));
                    } else {
                        balance.setCashSum(balance.getCashSum().subtract(itemArrivalDetails.getSum()));
                    }
                } else {
                    balance.setCreditSum(balance.getCreditSum().add(itemArrivalDetails.getSumOfCredit()));
                    if (itemArrivalDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().subtract(itemArrivalDetails.getSum().subtract(itemArrivalDetails.getSumOfCredit())));
                    } else {
                        balance.setCashSum(balance.getCashSum().subtract(itemArrivalDetails.getSum().subtract(itemArrivalDetails.getSumOfCredit())));
                    }
                }
            }
        }
        balanceService.update(balance);

        if (itemArrivalDoc.getCreditDoc() != null) {
            itemArrivalDoc.getCreditDoc().setCreditDate(itemArrivalDoc.getItemArrivalDate());
        }
        itemArrivalDocDao.persist(itemArrivalDoc);
        return itemArrivalDoc;
    }

    @Override
    @Transactional
    public ItemArrivalDoc deactivate(ItemArrivalDoc itemArrivalDoc) {
        Balance balance = balanceService.findByShop(itemArrivalDoc.getShop());

        //update items(count, basePrice, price) from ItemArrivalDetailsList
        for (ItemArrivalDetails itemArrivalDetails : itemArrivalDoc.getItemArrivalDetailsList()) {

            if (itemArrivalDetails.isActivate()) {
                //set itemCount
                itemArrivalDetails.getItem().setCount(itemArrivalDetails.getItem().getCount().subtract(itemArrivalDetails.getCount()));

                //update item
                itemService.update(itemArrivalDetails.getItem());

                if (itemArrivalDetails.getSumOfCredit() == null) {
                    if (itemArrivalDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().add(itemArrivalDetails.getSum()));
                    } else {
                        balance.setCashSum(balance.getCashSum().add(itemArrivalDetails.getSum()));
                    }
                } else {
                    if (!itemArrivalDoc.getCreditDoc().isWrittenOff()) {
                        balance.setCreditSum(balance.getCreditSum().subtract(itemArrivalDetails.getSumOfCredit()));
                    }
                    if (itemArrivalDetails.isNonCash()) {
                        balance.setNonCashSum(balance.getNonCashSum().add(itemArrivalDetails.getSum().subtract(itemArrivalDetails.getSumOfCredit())));
                    } else {
                        balance.setCashSum(balance.getCashSum().add(itemArrivalDetails.getSum().subtract(itemArrivalDetails.getSumOfCredit())));
                    }
                }
                itemArrivalDetails.setActivate(false);
                itemArrivalDetails.setDeactivateDate(LocalDate.now());
            }
        }
        balanceService.update(balance);

        if (itemArrivalDoc.getCreditDoc() != null) {
            itemArrivalDoc.getCreditDoc().setActivate(false);
            itemArrivalDoc.getCreditDoc().setDeactivateDate(LocalDate.now());
        }
        itemArrivalDoc.setActivate(false);
        itemArrivalDoc.setDeactivateDate(LocalDate.now());
        itemArrivalDocDao.update(itemArrivalDoc);
        return itemArrivalDoc;
    }
}