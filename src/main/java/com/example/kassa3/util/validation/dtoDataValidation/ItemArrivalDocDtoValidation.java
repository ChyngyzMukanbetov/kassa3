package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDetailsDto;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDocDto;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class ItemArrivalDocDtoValidation {

    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final ItemService itemService;

    public ItemArrivalDocDto validate(ItemArrivalDocDto itemArrivalDocDto, Principal principal) {

        if (itemArrivalDocDto.getId() != null) {
            throw new ItemAddSellWriteOffException("601", "id ItemArrivalDocDto должно быть пустым");
        }

        if (!userService.existsById(itemArrivalDocDto.getUserId())) {
            throw new ItemAddSellWriteOffException("602", "User с таким id отсутствует, userId:" + itemArrivalDocDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemArrivalDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("603", "Вы указали не Ваш userId в поле itemArrivalDocDto.userId");
//        }

        if (!shopService.existsById(itemArrivalDocDto.getShopId())) {
            throw new ItemAddSellWriteOffException("604", "Shop с таким id отсутствует: shopId:" + itemArrivalDocDto.getShopId());
        }

//        if (!shopService.findById(itemArrivalDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("605", "Shop с таким id отсутствует:" + itemArrivalDocDto.getShopId());
//        }


        if (itemArrivalDocDto.getPartnerId() != null) {
            if (!partnerService.existsById(itemArrivalDocDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("606", "Partner с таким id отсутствует, partnerId:" + itemArrivalDocDto.getPartnerId());
//            } else if (!partnerService.findById(itemArrivalDocDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("607", "Partner с таким id отсутствует:" + itemArrivalDocDto.getPartnerId());
            }
        }


        for (
                ItemArrivalDetailsDto itemArrivalDetailsDto : itemArrivalDocDto.getItemArrivalDetailsList()) {
            if (itemArrivalDetailsDto.getId() != null) {
                throw new ItemAddSellWriteOffException("608", "все itemArrivalDetailsDto.id должно быть пустыми");
            }
        }

        BigDecimal totalSumOfCredit = BigDecimal.ZERO;
        for (ItemArrivalDetailsDto itemArrivalDetailsDto : itemArrivalDocDto.getItemArrivalDetailsList()) {
            if (!itemService.existsById(itemArrivalDetailsDto.getItemId())) {
                throw new ItemAddSellWriteOffException("609", "Товар с таким id отсутствует, itemId: " + itemArrivalDetailsDto.getItemId());
            }
//            if (itemService.findById(itemArrivalDetailsDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("610", "Товар с таким id отсутствует, itemId: " + itemArrivalDetailsDto.getItemId());
//            }
            if (!itemService.findById(itemArrivalDetailsDto.getItemId()).isActivate()) {
                throw new ItemAddSellWriteOffException("611", "Товар с таким id деактивирован, itemId: " + itemArrivalDetailsDto.getItemId());
            }
            if (!itemArrivalDetailsDto.getBasePrice().multiply(itemArrivalDetailsDto.getCount()).equals(itemArrivalDetailsDto.getSum())) {
                throw new ItemAddSellWriteOffException("612", "Сумма(sum) должна быть равна цена оприходования(basePrice) * количество(count), itemId: " + itemArrivalDetailsDto.getItemId());
            }
            if ((itemArrivalDetailsDto.getSumOfCredit().compareTo(itemArrivalDetailsDto.getSum())) > 0) {
                throw new ItemAddSellWriteOffException("613", "Сумма в долг(sumOfCredit) не должна быть больше суммы(sum), itemId: " + itemArrivalDetailsDto.getItemId());
            }
            if (itemArrivalDetailsDto.getSumOfCredit() != null) {
                totalSumOfCredit = totalSumOfCredit.add(itemArrivalDetailsDto.getSumOfCredit());
            }
        }
        if ((totalSumOfCredit.compareTo(itemArrivalDocDto.getCreditDoc().getSumOfCredit())) != 0) {
            throw new ItemAddSellWriteOffException("614", "Сумма долга(sumOfCredit) в CreditDoc должна быть равна общей сумме долга(sumOfCredit) во всех itemArrivalDetailsDto");
        }

        if (itemArrivalDocDto.getCreditDoc() == null) {
            for (ItemArrivalDetailsDto itemArrivalDetailsDto : itemArrivalDocDto.getItemArrivalDetailsList()) {
                if (itemArrivalDetailsDto.getOnCredit()) {
                    throw new ItemAddSellWriteOffException("615", "Если товар закуплен в долг, то CreditDoc обязателен. itemId: " + itemArrivalDetailsDto.getItemId());
                }
            }
        }

        if (itemArrivalDocDto.getCreditDoc() != null) {
            if (itemArrivalDocDto.getCreditDoc().getId() != null) {
                throw new ItemAddSellWriteOffException("617", "id CreditDoc должно быть пустым");
            }
            boolean isOnCreditExist = false;
            for (ItemArrivalDetailsDto itemArrivalDetailsDto : itemArrivalDocDto.getItemArrivalDetailsList()) {
                if (itemArrivalDetailsDto.getOnCredit()) {
                    isOnCreditExist = true;
                    if (itemArrivalDetailsDto.getSumOfCredit() == null) {
                        throw new ItemAddSellWriteOffException("618", "Товар в долг, укажите сумму в поле SumOfCredit");
                    }
                    if (itemArrivalDetailsDto.getSumOfCredit().equals(BigDecimal.ZERO)) {
                        throw new ItemAddSellWriteOffException("619", "Товар в долг, укажите сумму в поле SumOfCredit");
                    }
                }
                if (!isOnCreditExist) {
                    throw new ItemAddSellWriteOffException("620", "Нет товаров приобретаемых в долг, CreditDoc должен отсутствовать");
                }
            }

            if (!itemArrivalDocDto.getCreditDoc().getUserId().equals(itemArrivalDocDto.getUserId())) {
                throw new ItemAddSellWriteOffException("621", "userId основного документа и userId в CreditDoc должны быть идентичны");
            }
            if (!itemArrivalDocDto.getCreditDoc().getShopId().equals(itemArrivalDocDto.getShopId())) {
                throw new ItemAddSellWriteOffException("622", "userId основного документа и userId в CreditDoc должны быть идентичны");
            }

            if ((itemArrivalDocDto.getCreditDoc().getPartnerId() == null & itemArrivalDocDto.getPartnerId() != null)
                || (itemArrivalDocDto.getCreditDoc().getPartnerId() != null & itemArrivalDocDto.getPartnerId() == null)) {
                throw new ItemAddSellWriteOffException("623", "Partner основного документа и Partner в CreditDoc должны быть идентичны");
            }
            if (itemArrivalDocDto.getCreditDoc().getPartnerId() != null & itemArrivalDocDto.getPartnerId() != null
                    & !itemArrivalDocDto.getCreditDoc().getPartnerId().equals(itemArrivalDocDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("624", "Partner основного документа и Partner в CreditDoc должны быть идентичны");
            }
        }

        return itemArrivalDocDto;
    }
}
