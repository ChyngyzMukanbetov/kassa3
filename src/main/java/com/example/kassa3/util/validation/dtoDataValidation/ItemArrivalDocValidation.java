package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.ItemArrivalDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.CreditPaymentDoc;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitIncomeDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDetailsCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDocCreateDto;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.ItemArrivalDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class ItemArrivalDocValidation {

    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final ItemService itemService;
    private final ItemArrivalDocService itemArrivalDocService;
    private final ItemArrivalDocCreateConverter itemArrivalDocCreateConverter;

    @Transactional
    public ItemArrivalDoc validateAndPersist(ItemArrivalDocCreateDto itemArrivalDocCreateDto, Principal principal) {

        if (!userService.existsById(itemArrivalDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("601", "User с таким id отсутствует, userId:" + itemArrivalDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemArrivalDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("602", "Вы указали не Ваш userId в поле itemArrivalDocDto.userId");
//        }

        if (!shopService.existsById(itemArrivalDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("603", "Shop с таким id отсутствует: shopId:" + itemArrivalDocCreateDto.getShopId());
        }

//        if (!shopService.findById(itemArrivalDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("604", "Shop с таким id отсутствует:" + itemArrivalDocDto.getShopId());
//        }


        if (itemArrivalDocCreateDto.getPartnerId() != null) {
            if (!partnerService.existsById(itemArrivalDocCreateDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("605", "Partner с таким id отсутствует, partnerId:" + itemArrivalDocCreateDto.getPartnerId());
//            } else if (!partnerService.findById(itemArrivalDocDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("606", "Partner с таким id отсутствует:" + itemArrivalDocDto.getPartnerId());
            }
        }

        BigDecimal totalSumOfCredit = BigDecimal.ZERO;
        for (ItemArrivalDetailsCreateDto itemArrivalDetailsCreateDto : itemArrivalDocCreateDto.getItemArrivalDetailsList()) {

            if (!itemService.existsById(itemArrivalDetailsCreateDto.getItemId())) {
                throw new ItemAddSellWriteOffException("607", "Товар с таким id отсутствует, itemId: " + itemArrivalDetailsCreateDto.getItemId());
            }
//            if (itemService.findById(itemArrivalDetailsDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("608", "Товар с таким id отсутствует, itemId: " + itemArrivalDetailsDto.getItemId());
//            }
            if (!itemService.findById(itemArrivalDetailsCreateDto.getItemId()).isActivate()) {
                throw new ItemAddSellWriteOffException("609", "Товар с таким id деактивирован, itemId: " + itemArrivalDetailsCreateDto.getItemId());
            }
            if (!itemArrivalDetailsCreateDto.getBasePrice().multiply(itemArrivalDetailsCreateDto.getCount()).equals(itemArrivalDetailsCreateDto.getSum())) {
                throw new ItemAddSellWriteOffException("610", "Сумма(sum) должна быть равна цена оприходования(basePrice) * количество(count), itemId: " + itemArrivalDetailsCreateDto.getItemId());
            }
            if (itemArrivalDetailsCreateDto.getSumOfCredit() != null) {
                if (!itemArrivalDetailsCreateDto.isOnCredit()) {
                    throw new ItemAddSellWriteOffException("611", "Если товар не в долг(OnCredit = false) то Суммы в долг(sumOfCredit) не должна быть, itemId: " + itemArrivalDetailsCreateDto.getItemId());
                }
                if ((itemArrivalDetailsCreateDto.getSumOfCredit().compareTo(itemArrivalDetailsCreateDto.getSum())) > 0) {
                    throw new ItemAddSellWriteOffException("612", "Сумма в долг(sumOfCredit) не должна быть больше суммы(sum), itemId: " + itemArrivalDetailsCreateDto.getItemId());
                }
                totalSumOfCredit = totalSumOfCredit.add(itemArrivalDetailsCreateDto.getSumOfCredit());
            }
        }

        if (itemArrivalDocCreateDto.getCreditDoc() == null) {
            for (ItemArrivalDetailsCreateDto itemArrivalDetailsCreateDto : itemArrivalDocCreateDto.getItemArrivalDetailsList()) {
                if (itemArrivalDetailsCreateDto.isOnCredit()) {
                    throw new ItemAddSellWriteOffException("613", "Если товар закуплен в долг, то CreditDoc обязателен. itemId: " + itemArrivalDetailsCreateDto.getItemId());
                }
            }
        } else {
            boolean isOnCreditExist = false;
            for (ItemArrivalDetailsCreateDto itemArrivalDetailsCreateDto : itemArrivalDocCreateDto.getItemArrivalDetailsList()) {
                if (itemArrivalDetailsCreateDto.isOnCredit()) {
                    isOnCreditExist = true;
                    if (itemArrivalDetailsCreateDto.getSumOfCredit() == null) {
                        throw new ItemAddSellWriteOffException("614", "Товар в долг, укажите сумму в поле SumOfCredit");
                    }
                    if (itemArrivalDetailsCreateDto.getSumOfCredit().equals(BigDecimal.ZERO)) {
                        throw new ItemAddSellWriteOffException("615", "Товар в долг, укажите сумму в поле SumOfCredit");
                    }
                }
                if (!isOnCreditExist) {
                    throw new ItemAddSellWriteOffException("616", "Нет товаров приобретаемых в долг, CreditDoc должен отсутствовать");
                }
            }

            if (!itemArrivalDocCreateDto.getCreditDoc().getUserId().equals(itemArrivalDocCreateDto.getUserId())) {
                throw new ItemAddSellWriteOffException("617", "userId основного документа и userId в CreditDoc должны быть идентичны");
            }
            if (!itemArrivalDocCreateDto.getCreditDoc().getShopId().equals(itemArrivalDocCreateDto.getShopId())) {
                throw new ItemAddSellWriteOffException("618", "shopId основного документа и userId в CreditDoc должны быть идентичны");
            }
            if (itemArrivalDocCreateDto.getCreditDoc().getReturnPlanDate().isBefore(itemArrivalDocCreateDto.getItemArrivalDate())) {
                throw new ItemAddSellWriteOffException("619", "Дата возврата долга (returnPlanDate) не должна быть раньше даты приобретения(itemArrivalDate)");
            }

            if ((itemArrivalDocCreateDto.getCreditDoc().getPartnerId() == null & itemArrivalDocCreateDto.getPartnerId() != null)
                    || (itemArrivalDocCreateDto.getCreditDoc().getPartnerId() != null & itemArrivalDocCreateDto.getPartnerId() == null)) {
                throw new ItemAddSellWriteOffException("620", "Partner основного документа и Partner в CreditDoc должны быть идентичны");
            }
            if (itemArrivalDocCreateDto.getCreditDoc().getPartnerId() != null & itemArrivalDocCreateDto.getPartnerId() != null
                    & !itemArrivalDocCreateDto.getCreditDoc().getPartnerId().equals(itemArrivalDocCreateDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("621", "Partner основного документа и Partner в CreditDoc должны быть идентичны");
            }
            if (itemArrivalDocCreateDto.getCreditDoc().getSumOfCredit() != null) {
                if ((totalSumOfCredit.compareTo(itemArrivalDocCreateDto.getCreditDoc().getSumOfCredit())) != 0) {
                    throw new ItemAddSellWriteOffException("622", "Сумма долга(sumOfCredit) в CreditDoc должна быть равна общей сумме долга(sumOfCredit) во всех itemArrivalDetailsDto");
                }
            }
        }
        ItemArrivalDoc itemArrivalDoc = itemArrivalDocCreateConverter.toModel(itemArrivalDocCreateDto);
        itemArrivalDocService.persist(itemArrivalDoc);
        return itemArrivalDoc;
    }

    @Transactional
    public ItemArrivalDoc validateAndDeactivate(Long itemArrivalDocId, Principal principal) {
        if (itemArrivalDocId == null) {
            throw new ItemAddSellWriteOffException("623", "itemArrivalDocId не должно быть null");
        }
        if (!itemArrivalDocService.existsById(itemArrivalDocId)) {
            throw new ItemAddSellWriteOffException("624", "itemArrivalDoc с таким id отсутствует, itemArrivalDocId:" + itemArrivalDocId);
        }

        ItemArrivalDoc itemArrivalDoc = itemArrivalDocService.findById(itemArrivalDocId);

//        if (!userService.findByUsername(principal.getName()).equals(itemArrivalDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("625", "itemArrivalDoc с таким id отсутствует, itemArrivalDocId:" + itemArrivalDocId);
//        }
        if (!itemArrivalDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("626", "itemArrivalDoc уже является деактивированным: itemArrivalDocId:" + itemArrivalDocId);
        }
        if (itemArrivalDoc.getCreditDoc() != null) {
            if (itemArrivalDoc.getCreditDoc().getCreditPaymentDocList() != null) {
                if (itemArrivalDoc.getCreditDoc().getCreditPaymentDocList().size() > 0) {
                    int numberOfActivateCreditPaymentDocs = 0;
                    for (CreditPaymentDoc creditPaymentDoc : itemArrivalDoc.getCreditDoc().getCreditPaymentDocList()) {
                        if (creditPaymentDoc.isActivate()) {
                            numberOfActivateCreditPaymentDocs++;
                        }
                    }
                    if (numberOfActivateCreditPaymentDocs > 0) {
                        throw new ItemAddSellWriteOffException("627", "Оприходование(itemArrivalDoc) не может быть аннулировано, так как имеются платежи(CreditPaymentDoc) по кредиту (CreditDoc), CreditDocId:"
                        + itemArrivalDoc.getCreditDoc().getId() + " , пожалуйста аннулируйте платежи по данному кредиту");
                    }
                }
            }
        }
        return itemArrivalDocService.deactivate(itemArrivalDoc);
    }

    @Transactional
    public ItemArrivalDoc validateAndChangeOldToNew(Long itemArrivalDocId, ItemArrivalDocCreateDto itemArrivalDocCreateDto, Principal principal) {
        validateAndDeactivate(itemArrivalDocId, principal);
        return validateAndPersist(itemArrivalDocCreateDto, principal);
    }
}
