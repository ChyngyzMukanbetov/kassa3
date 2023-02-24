package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.ItemWriteOffDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDetailsCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDocCreateDto;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
public class ItemWriteOffDocValidation {

    private final UserService userService;
    private final ShopService shopService;
    private final ItemService itemService;
    private final ItemWriteOffDocService itemWriteOffDocService;
    private final ItemWriteOffDocCreateConverter itemWriteOffDocCreateConverter;

    @Transactional
    public ItemWriteOffDoc validateAndPersist(ItemWriteOffDocCreateDto itemWriteOffDocCreateDto, Principal principal) {
        if (!userService.existsById(itemWriteOffDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("801", "User с таким id отсутствует, userId:" + itemWriteOffDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemWriteOffDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("802", "Вы указали не Ваш userId в поле itemWriteOffDocDto.userId");
//        }

        if (!shopService.existsById(itemWriteOffDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("803", "Shop с таким id отсутствует: shopId:" + itemWriteOffDocCreateDto.getShopId());
        }

//        if (!shopService.findById(itemWriteOffDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("804", "Shop с таким id отсутствует:" + itemWriteOffDocDto.getShopId());
//        }

        for (ItemWriteOffDetailsCreateDto itemWriteOffDetailsCreateDto : itemWriteOffDocCreateDto.getItemWriteOffDetailsList()) {

            if (!itemService.existsById(itemWriteOffDetailsCreateDto.getItemId())) {
                throw new ItemAddSellWriteOffException("807", "Товар с таким id отсутствует, itemId: " + itemWriteOffDetailsCreateDto.getItemId());
            }
//            if (itemService.findById(itemWriteOffDetailsDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("808", "Товар с таким id отсутствует, itemId: " + itemWriteOffDetailsDto.getItemId());
//            }
            if (!itemService.findById(itemWriteOffDetailsCreateDto.getItemId()).isActivate()) {
                throw new ItemAddSellWriteOffException("809", "Товар с таким id деактивирован, itemId: " + itemWriteOffDetailsCreateDto.getItemId());
            }
            if (itemService.findById(itemWriteOffDetailsCreateDto.getItemId()).getCount().compareTo(itemWriteOffDetailsCreateDto.getCount()) < 0) {
                throw new ItemAddSellWriteOffException("810", "Превышено количества товара в наличии, itemId: "
                        + itemWriteOffDetailsCreateDto.getItemId() + ", имеется на складе: " + itemService.findById(itemWriteOffDetailsCreateDto.getItemId()).getCount());
            }
            if (itemWriteOffDetailsCreateDto.getBasePrice().multiply(itemWriteOffDetailsCreateDto.getCount()).equals(itemWriteOffDetailsCreateDto.getSum())) {
                throw new ItemAddSellWriteOffException("810", "Сумма(sum) должна быть равна цена оприходования (BasePrice) * количество(count), itemId: " + itemWriteOffDetailsCreateDto.getItemId());
            }
        }
        ItemWriteOffDoc itemWriteOffDoc = itemWriteOffDocCreateConverter.toModel(itemWriteOffDocCreateDto);
        itemWriteOffDocService.persist(itemWriteOffDoc);
        return itemWriteOffDoc;
    }

    @Transactional
    public ItemWriteOffDoc validateAndDeactivate(Long itemWriteOffDocId, Principal principal) {
        if (itemWriteOffDocId == null) {
            throw new ItemAddSellWriteOffException("811", "itemWriteOffDocId не должно быть null");
        }
        if (!itemWriteOffDocService.existsById(itemWriteOffDocId)) {
            throw new ItemAddSellWriteOffException("812", "itemWriteOffDoc с таким id отсутствует, itemWriteOffDocId:" + itemWriteOffDocId);
        }

        ItemWriteOffDoc itemWriteOffDoc = itemWriteOffDocService.findById(itemWriteOffDocId);

//        if (!userService.findByUsername(principal.getName()).equals(itemWriteOffDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("813", "itemWriteOffDoc с таким id отсутствует, itemWriteOffDocId:" + itemWriteOffDocId);
//        }
        if (!itemWriteOffDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("814", "itemWriteOffDoc уже является деактивированным: itemWriteOffDocId:" + itemWriteOffDocId);
        }

        return itemWriteOffDocService.deactivate(itemWriteOffDoc);
    }

    @Transactional
    public ItemWriteOffDoc validateAndChangeOldToNew(Long itemWriteOffDocId, ItemWriteOffDocCreateDto itemWriteOffDocCreateDto, Principal principal) {
        validateAndDeactivate(itemWriteOffDocId, principal);
        return validateAndPersist(itemWriteOffDocCreateDto, principal);
    }
}