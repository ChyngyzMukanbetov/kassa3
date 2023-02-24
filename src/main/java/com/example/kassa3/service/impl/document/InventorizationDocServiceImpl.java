package com.example.kassa3.service.impl.document;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.dao.abstracts.document.InventorizationDocDao;
import com.example.kassa3.model.document.InventorizationDetails;
import com.example.kassa3.model.document.InventorizationDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.document.InventorizationDocService;
import com.example.kassa3.service.impl.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InventorizationDocServiceImpl extends ReadWriteServiceImpl<InventorizationDoc, Long> implements InventorizationDocService {

    private final InventorizationDocDao inventorizationDocDao;
    private final ItemService itemService;
    private final BalanceService balanceService;

    public InventorizationDocServiceImpl(InventorizationDocDao inventorizationDocDao, ItemService itemService, BalanceService balanceService) {
        super(inventorizationDocDao);
        this.inventorizationDocDao = inventorizationDocDao;
        this.itemService = itemService;
        this.balanceService = balanceService;
    }

    @Override
    @Transactional
    public InventorizationDoc persist(InventorizationDoc inventorizationDoc) {

        if (!inventorizationDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1303", "Попытка сохранить(persist) неактивный(activate = false) inventorizationDoc в Service");
        }

        //update items(count, basePrice, price) from InventorizationDetailsList
        if (!inventorizationDoc.isItemInventorizationActivate()) {
            for (InventorizationDetails inventorizationDetails : inventorizationDoc.getInventorizationDetailsList()) {
                if (inventorizationDetails.isActivate()) {
                    //set itemCount
                    inventorizationDetails.setCountDifference(inventorizationDetails.getCountNew().subtract(inventorizationDetails.getCountOld()));
                    inventorizationDetails.getItem().setCount(inventorizationDetails.getItem().getCount().add(inventorizationDetails.getCountDifference()));
                    //update item
                    itemService.update(inventorizationDetails.getItem());
                }
            }
        }

        if (inventorizationDoc.isBalanceInventorizationActivate()) {
            Balance balance = balanceService.findByShop(inventorizationDoc.getShop());
            inventorizationDoc.setCashSumDifference(inventorizationDoc.getCashSumNew().subtract(inventorizationDoc.getCashSumOld()));
            balance.setCashSum(balance.getCashSum().add(inventorizationDoc.getCashSumDifference()));

            inventorizationDoc.setNonCashSumDifference(inventorizationDoc.getNonCashSumNew().subtract(inventorizationDoc.getNonCashSumOld()));
            balance.setNonCashSum(balance.getNonCashSum().add(inventorizationDoc.getNonCashSumDifference()));
            balanceService.update(balance);
        }

        inventorizationDocDao.persist(inventorizationDoc);
        return inventorizationDoc;
    }

    @Override
    @Transactional
    public InventorizationDoc deactivate(InventorizationDoc inventorizationDoc) {
        //update items(count, basePrice, price) from InventorizationDetailsList
        if (!inventorizationDoc.isItemInventorizationActivate()) {
            for (InventorizationDetails inventorizationDetails : inventorizationDoc.getInventorizationDetailsList()) {
                if (inventorizationDetails.isActivate()) {
                    //set itemCount
                    inventorizationDetails.getItem().setCount(inventorizationDetails.getItem().getCount().subtract(inventorizationDetails.getCountDifference()));
                    //update item
                    itemService.update(inventorizationDetails.getItem());
                }
                inventorizationDetails.setActivate(false);
                inventorizationDetails.setDeactivateDate(LocalDate.now());
            }
        }

        if (inventorizationDoc.isBalanceInventorizationActivate()) {
            Balance balance = balanceService.findByShop(inventorizationDoc.getShop());
            balance.setCashSum(balance.getCashSum().subtract(inventorizationDoc.getCashSumDifference()));
            balance.setNonCashSum(balance.getNonCashSum().subtract(inventorizationDoc.getNonCashSumDifference()));
            balanceService.update(balance);
        }
        inventorizationDoc.setActivate(false);
        inventorizationDoc.setDeactivateDate(LocalDate.now());
        inventorizationDocDao.update(inventorizationDoc);

        return inventorizationDoc;
    }
}