package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.InventorizationDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.document.InventorizationDoc;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitIncomeDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDetailsCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDocCreateDto;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.InventorizationDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
public class InventorizationDocValidation {

    private final UserService userService;
    private final ShopService shopService;
    private final ItemService itemService;
    private final BalanceService balanceService;
    private final InventorizationDocService inventorizationDocService;
    private final InventorizationDocCreateConverter inventorizationDocCreateConverter;

    @Transactional
    public InventorizationDoc validateAndPersist(InventorizationDocCreateDto inventorizationDocCreateDto, Principal principal) {

        if (!userService.existsById(inventorizationDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("2001", "User с таким id отсутствует, userId:" + inventorizationDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(inventorizationDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("2002", "Вы указали не Ваш userId в поле inventorizationDocDto.userId");
//        }

        if (!shopService.existsById(inventorizationDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("2003", "Shop с таким id отсутствует: shopId:" + inventorizationDocCreateDto.getShopId());
        }

//        if (!shopService.findById(inventorizationDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("2004", "Shop с таким id отсутствует:" + inventorizationDocDto.getShopId());
//        }

        if (!inventorizationDocCreateDto.isItemInventorizationActivate()) {
            if (inventorizationDocCreateDto.getInventorizationDetailsList() != null) {
                throw new ItemAddSellWriteOffException("2005", "Если ItemInventorizationActivate = false, то InventorizationDetails должен быть null");
            }
        } else {
            if (inventorizationDocCreateDto.getInventorizationDetailsList() == null) {
                throw new ItemAddSellWriteOffException("2006", "Если ItemInventorizationActivate = true, то InventorizationDetails обязательно");
            } else if (inventorizationDocCreateDto.getInventorizationDetailsList().size() == 0) {
                throw new ItemAddSellWriteOffException("2007", "Если ItemInventorizationActivate = true, то должен быть хотя бы один InventorizationDetails");
            }
            for (InventorizationDetailsCreateDto inventorizationDetailsCreateDto : inventorizationDocCreateDto.getInventorizationDetailsList()) {
                if (itemService.existsById(inventorizationDetailsCreateDto.getItemId())) {
                    throw new ItemAddSellWriteOffException("2008", "Товар с таким id отсутствует, itemId: " + inventorizationDetailsCreateDto.getItemId());
                }
//                if (itemService.findById(inventorizationDetailsCreateDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                    throw new ItemAddSellWriteOffException("2009", "Товар с таким id отсутствует, itemId: " + inventorizationDetailsCreateDto.getItemId());
//                }
                if (!itemService.findById(inventorizationDetailsCreateDto.getItemId()).isActivate()) {
                    throw new ItemAddSellWriteOffException("2010", "Товар с таким id деактивирован, itemId: " + inventorizationDetailsCreateDto.getItemId());
                }
                if (!itemService.findById(inventorizationDetailsCreateDto.getItemId()).getCount().equals(inventorizationDetailsCreateDto.getCountOld())) {
                    throw new ItemAddSellWriteOffException("2011", "Количество товара по учету (CountOld) отличается от количества товара в Базе Данных, кол-во товара в БД: " + itemService.findById(inventorizationDetailsCreateDto.getItemId()).getCount() + ", itemId: " + inventorizationDetailsCreateDto.getItemId());
                }
            }
        }

        if (inventorizationDocCreateDto.isBalanceInventorizationActivate()) {
            if (!balanceService.findByShop(shopService.findById(inventorizationDocCreateDto.getShopId())).getCashSum().equals(inventorizationDocCreateDto.getCashSumOld())) {
                throw new ItemAddSellWriteOffException("2012", "Сумма наличных среств по учету(CashSumOld) отличается от суммы наличных средств в Базе Данных, сумма наличных средств в БД: " + balanceService.findByShop(shopService.findById(inventorizationDocCreateDto.getShopId())).getCashSum());
            }
            if (!balanceService.findByShop(shopService.findById(inventorizationDocCreateDto.getShopId())).getNonCashSum().equals(inventorizationDocCreateDto.getNonCashSumOld())) {
                throw new ItemAddSellWriteOffException("2013", "Сумма безналичных среств по учету(NonCashSumOld) отличается от суммы безналичных средств в Базе Данных, сумма безналичных средств в БД: " + balanceService.findByShop(shopService.findById(inventorizationDocCreateDto.getShopId())).getNonCashSum());
            }
        }
        InventorizationDoc inventorizationDoc = inventorizationDocCreateConverter.toModel(inventorizationDocCreateDto);
        inventorizationDocService.persist(inventorizationDoc);
        return inventorizationDoc;
    }

    @Transactional
    public InventorizationDoc validateAndDeactivate(Long inventorizationDocId, Principal principal) {
        if (inventorizationDocId == null) {
            throw new ItemAddSellWriteOffException("2014", "inventorizationDocId не должно быть null");
        }
        if (!inventorizationDocService.existsById(inventorizationDocId)) {
            throw new ItemAddSellWriteOffException("2015", "inventorizationDoc с таким id отсутствует, inventorizationDocId:" + inventorizationDocId);
        }

        InventorizationDoc inventorizationDoc = inventorizationDocService.findById(inventorizationDocId);

//        if (!userService.findByUsername(principal.getName()).equals(inventorizationDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("2016", "inventorizationDoc с таким id отсутствует, inventorizationDocId:" + inventorizationDocId);
//        }
        if (!inventorizationDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("2017", "inventorizationDoc уже является деактивированным: inventorizationDocId:" + inventorizationDocId);
        }
        return inventorizationDocService.deactivate(inventorizationDoc);
    }

    @Transactional
    public InventorizationDoc validateAndChangeOldToNew(Long inventorizationDocId, InventorizationDocCreateDto inventorizationDocCreateDto, Principal principal) {
        validateAndDeactivate(inventorizationDocId, principal);
        return validateAndPersist(inventorizationDocCreateDto, principal);
    }
}
