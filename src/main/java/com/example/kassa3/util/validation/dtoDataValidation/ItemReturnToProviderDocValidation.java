package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.ItemReturnToProviderDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDetailsCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDocCreateDto;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.ItemReturnToProviderDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
public class ItemReturnToProviderDocValidation {

    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final ItemService itemService;
    private final ItemReturnToProviderDocService itemReturnToProviderDocService;
    private final ItemReturnToProviderDocCreateConverter itemReturnToProviderDocCreateConverter;

    @Transactional
    public ItemReturnToProviderDoc validateAndPersist(ItemReturnToProviderDocCreateDto itemReturnToProviderDocCreateDto, Principal principal) {

        if (!userService.existsById(itemReturnToProviderDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1901", "User с таким id отсутствует, userId:" + itemReturnToProviderDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemReturnToProviderDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1902", "Вы указали не Ваш userId в поле itemReturnToProviderDocDto.userId");
//        }

        if (!shopService.existsById(itemReturnToProviderDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1903", "Shop с таким id отсутствует: shopId:" + itemReturnToProviderDocCreateDto.getShopId());
        }

//        if (!shopService.findById(itemReturnToProviderDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1904", "Shop с таким id отсутствует:" + itemReturnToProviderDocDto.getShopId());
//        }


        if (itemReturnToProviderDocCreateDto.getPartnerId() != null) {
            if (!partnerService.existsById(itemReturnToProviderDocCreateDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("1905", "Partner с таким id отсутствует, partnerId:" + itemReturnToProviderDocCreateDto.getPartnerId());
//            } else if (!partnerService.findById(itemReturnToProviderDocDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("1906", "Partner с таким id отсутствует:" + itemReturnToProviderDocDto.getPartnerId());
            }
        }

        for (ItemReturnToProviderDetailsCreateDto itemReturnToProviderDetailsCreateDto : itemReturnToProviderDocCreateDto.getItemReturnToProviderDetailsList()) {

            if (!itemService.existsById(itemReturnToProviderDetailsCreateDto.getItemId())) {
                throw new ItemAddSellWriteOffException("1907", "Товар с таким id отсутствует, itemId: " + itemReturnToProviderDetailsCreateDto.getItemId());
            }
//            if (itemService.findById(itemReturnToProviderDetailsDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("1908", "Товар с таким id отсутствует, itemId: " + itemReturnToProviderDetailsDto.getItemId());
//            }
            if (!itemService.findById(itemReturnToProviderDetailsCreateDto.getItemId()).isActivate()) {
                throw new ItemAddSellWriteOffException("1909", "Товар с таким id деактивирован, itemId: " + itemReturnToProviderDetailsCreateDto.getItemId());
            }
            if (itemService.findById(itemReturnToProviderDetailsCreateDto.getItemId()).getCount().compareTo(itemReturnToProviderDetailsCreateDto.getCount()) < 0) {
                throw new ItemAddSellWriteOffException("1910", "Превышено количества товара в наличии, itemId: "
                        + itemReturnToProviderDetailsCreateDto.getItemId() + ", имеется на складе: " + itemService.findById(itemReturnToProviderDetailsCreateDto.getItemId()).getCount());
            }
            if (itemReturnToProviderDetailsCreateDto.getPrice().multiply(itemReturnToProviderDetailsCreateDto.getCount()).equals(itemReturnToProviderDetailsCreateDto.getSum())) {
                throw new ItemAddSellWriteOffException("1911", "Сумма(sum) должна быть равна цена (Price) * количество(count), itemId: " + itemReturnToProviderDetailsCreateDto.getItemId());
            }
        }
        ItemReturnToProviderDoc itemReturnToProviderDoc = itemReturnToProviderDocCreateConverter.toModel(itemReturnToProviderDocCreateDto);
        itemReturnToProviderDocService.persist(itemReturnToProviderDoc);
        return itemReturnToProviderDoc;
    }

    @Transactional
    public ItemReturnToProviderDoc validateAndDeactivate(Long itemReturnToProviderDocId, Principal principal) {
        if (itemReturnToProviderDocId == null) {
            throw new ItemAddSellWriteOffException("1912", "itemReturnToProviderDocId не должно быть null");
        }
        if (!itemReturnToProviderDocService.existsById(itemReturnToProviderDocId)) {
            throw new ItemAddSellWriteOffException("1913", "itemReturnToProviderDoc с таким id отсутствует, itemReturnToProviderDocId:" + itemReturnToProviderDocId);
        }

        ItemReturnToProviderDoc itemReturnToProviderDoc = itemReturnToProviderDocService.findById(itemReturnToProviderDocId);

//        if (!userService.findByUsername(principal.getName()).equals(itemReturnToProviderDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("1914", "itemReturnToProviderDoc с таким id отсутствует, itemReturnToProviderDocId:" + itemReturnToProviderDocId);
//        }
        if (!itemReturnToProviderDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1915", "itemReturnToProviderDoc уже является деактивированным: itemReturnToProviderDocId:" + itemReturnToProviderDocId);
        }
        return itemReturnToProviderDocService.deactivate(itemReturnToProviderDoc);
    }

    @Transactional
    public ItemReturnToProviderDoc validateAndChangeOldToNew(Long itemReturnToProviderDocId, ItemReturnToProviderDocCreateDto itemReturnToProviderDocCreateDto, Principal principal) {
        validateAndDeactivate(itemReturnToProviderDocId, principal);
        return validateAndPersist(itemReturnToProviderDocCreateDto, principal);
    }
}
