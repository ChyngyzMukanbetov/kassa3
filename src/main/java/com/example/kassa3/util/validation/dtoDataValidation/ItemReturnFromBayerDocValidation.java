package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.ItemReturnFromBayerDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDetailsCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDocCreateDto;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.ItemReturnFromBayerDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
public class ItemReturnFromBayerDocValidation {
    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final ItemService itemService;
    private final ItemReturnFromBayerDocService itemReturnFromBayerDocService;
    private final ItemReturnFromBayerDocCreateConverter itemReturnFromBayerDocCreateConverter;

    @Transactional
    public ItemReturnFromBayerDoc validateAndPersist(ItemReturnFromBayerDocCreateDto itemReturnFromBayerDocCreateDto, Principal principal) {

        if (!userService.existsById(itemReturnFromBayerDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1801", "User с таким id отсутствует, userId:" + itemReturnFromBayerDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemReturnFromBayerDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1802", "Вы указали не Ваш userId в поле itemReturnFromBayerDocDto.userId");
//        }

        if (!shopService.existsById(itemReturnFromBayerDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1803", "Shop с таким id отсутствует: shopId:" + itemReturnFromBayerDocCreateDto.getShopId());
        }

//        if (!shopService.findById(itemReturnFromBayerDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1804", "Shop с таким id отсутствует:" + itemReturnFromBayerDocDto.getShopId());
//        }


        if (itemReturnFromBayerDocCreateDto.getPartnerId() != null) {
            if (!partnerService.existsById(itemReturnFromBayerDocCreateDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("1805", "Partner с таким id отсутствует, partnerId:" + itemReturnFromBayerDocCreateDto.getPartnerId());
//            } else if (!partnerService.findById(itemReturnFromBayerDocDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("1806", "Partner с таким id отсутствует:" + itemReturnFromBayerDocDto.getPartnerId());
            }
        }
        for (ItemReturnFromBayerDetailsCreateDto itemReturnFromBayerDetailsCreateDto : itemReturnFromBayerDocCreateDto.getItemReturnFromBayerDetailsList()) {

            if (!itemService.existsById(itemReturnFromBayerDetailsCreateDto.getItemId())) {
                throw new ItemAddSellWriteOffException("1807", "Товар с таким id отсутствует, itemId: " + itemReturnFromBayerDetailsCreateDto.getItemId());
            }
//            if (itemService.findById(itemReturnFromBayerDetailsDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("1808", "Товар с таким id отсутствует, itemId: " + itemReturnFromBayerDetailsDto.getItemId());
//            }
            if (!itemService.findById(itemReturnFromBayerDetailsCreateDto.getItemId()).isActivate()) {
                throw new ItemAddSellWriteOffException("1809", "Товар с таким id деактивирован, itemId: " + itemReturnFromBayerDetailsCreateDto.getItemId());
            }
            if (!itemReturnFromBayerDetailsCreateDto.getBasePrice().multiply(itemReturnFromBayerDetailsCreateDto.getCount()).equals(itemReturnFromBayerDetailsCreateDto.getSum())) {
                throw new ItemAddSellWriteOffException("1810", "Сумма(sum) должна быть равна цена оприходования(basePrice) * количество(count), itemId: " + itemReturnFromBayerDetailsCreateDto.getItemId());
            }
        }
        ItemReturnFromBayerDoc itemReturnFromBayerDoc = itemReturnFromBayerDocCreateConverter.toModel(itemReturnFromBayerDocCreateDto);
        itemReturnFromBayerDocService.persist(itemReturnFromBayerDoc);
        return itemReturnFromBayerDoc;
    }

    @Transactional
    public ItemReturnFromBayerDoc validateAndDeactivate(Long itemReturnFromBayerDocId, Principal principal) {
        if (itemReturnFromBayerDocId == null) {
            throw new ItemAddSellWriteOffException("1811", "itemReturnFromBayerDocId не должно быть null");
        }
        if (!itemReturnFromBayerDocService.existsById(itemReturnFromBayerDocId)) {
            throw new ItemAddSellWriteOffException("1812", "itemReturnFromBayerDoc с таким id отсутствует, itemReturnFromBayerDocId:" + itemReturnFromBayerDocId);
        }

        ItemReturnFromBayerDoc itemReturnFromBayerDoc = itemReturnFromBayerDocService.findById(itemReturnFromBayerDocId);

//        if (!userService.findByUsername(principal.getName()).equals(itemReturnFromBayerDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("1813", "itemReturnFromBayerDoc с таким id отсутствует, itemReturnFromBayerDocId:" + itemReturnFromBayerDocId);
//        }
        if (!itemReturnFromBayerDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1814", "itemReturnFromBayerDoc уже является деактивированным: itemReturnFromBayerDocId:" + itemReturnFromBayerDocId);
        }
        return itemReturnFromBayerDocService.deactivate(itemReturnFromBayerDoc);
    }

    @Transactional
    public ItemReturnFromBayerDoc validateAndChangeOldToNew(Long itemReturnFromBayerDocId, ItemReturnFromBayerDocCreateDto itemReturnFromBayerDocCreateDto, Principal principal) {
        validateAndDeactivate(itemReturnFromBayerDocId, principal);
        return validateAndPersist(itemReturnFromBayerDocCreateDto, principal);
    }
}
