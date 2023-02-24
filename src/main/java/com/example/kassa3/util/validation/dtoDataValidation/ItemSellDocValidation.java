package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.ItemSellDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.DebitIncomeDoc;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDetailsCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDocCreateDto;
import com.example.kassa3.service.abstracts.ItemService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.ItemSellDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

@Service
@AllArgsConstructor
public class ItemSellDocValidation {

    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final ItemService itemService;
    private final ItemSellDocService itemSellDocService;
    private final ItemSellDocCreateConverter itemSellDocCreateConverter;

    public ItemSellDoc validateAndPersist(ItemSellDocCreateDto itemSellDocCreateDto, Principal principal) {

        if (!userService.existsById(itemSellDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("701", "User с таким id отсутствует, userId:" + itemSellDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(itemSellDocDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("702", "Вы указали не Ваш userId в поле itemSellDocDto.userId");
//        }

        if (!shopService.existsById(itemSellDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("703", "Shop с таким id отсутствует: shopId:" + itemSellDocCreateDto.getShopId());
        }

//        if (!shopService.findById(itemSellDocDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("704", "Shop с таким id отсутствует:" + itemSellDocDto.getShopId());
//        }


        if (itemSellDocCreateDto.getPartnerId() != null) {
            if (!partnerService.existsById(itemSellDocCreateDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("705", "Partner с таким id отсутствует, partnerId:" + itemSellDocCreateDto.getPartnerId());
//            } else if (!partnerService.findById(itemSellDocDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("706", "Partner с таким id отсутствует:" + itemSellDocDto.getPartnerId());
            }
        }

        BigDecimal totalSumOfDebit = BigDecimal.ZERO;
        for (ItemSellDetailsCreateDto itemSellDetailsCreateDto : itemSellDocCreateDto.getItemSellDetailsList()) {

            if (!itemService.existsById(itemSellDetailsCreateDto.getItemId())) {
                throw new ItemAddSellWriteOffException("707", "Товар с таким id отсутствует, itemId: " + itemSellDetailsCreateDto.getItemId());
            }
//            if (itemService.findById(itemSellDetailsDto.getItemId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//                throw new ItemAddSellWriteOffException("708", "Товар с таким id отсутствует, itemId: " + itemSellDetailsDto.getItemId());
//            }
            if (!itemService.findById(itemSellDetailsCreateDto.getItemId()).isActivate()) {
                throw new ItemAddSellWriteOffException("709", "Товар с таким id деактивирован, itemId: " + itemSellDetailsCreateDto.getItemId());
            }
            if (itemService.findById(itemSellDetailsCreateDto.getItemId()).getCount().compareTo(itemSellDetailsCreateDto.getCount()) < 0) {
                throw new ItemAddSellWriteOffException("710", "Превышено количества товара в наличии, itemId: "
                        + itemSellDetailsCreateDto.getItemId() + ", имеется на складе: " + itemService.findById(itemSellDetailsCreateDto.getItemId()).getCount());
            }
            if (itemSellDetailsCreateDto.getPrice().multiply(itemSellDetailsCreateDto.getCount()).equals(itemSellDetailsCreateDto.getSum())) {
                throw new ItemAddSellWriteOffException("711", "Сумма(sum) должна быть равна цена (Price) * количество(count), itemId: " + itemSellDetailsCreateDto.getItemId());
            }

            if (itemSellDetailsCreateDto.getDiscountSum() != null) {
                if (!itemSellDetailsCreateDto.getSum().subtract(itemSellDetailsCreateDto.getDiscountSum()).equals(itemSellDetailsCreateDto.getTotalSum())) {
                    throw new ItemAddSellWriteOffException("712", "Итоговая сумма(totalSum) должна быть равна Сумма(sum) - Сумма скидки(discountSum), itemId: " + itemSellDetailsCreateDto.getItemId());
                }
                if (itemSellDetailsCreateDto.getDiscountSum().compareTo(itemSellDetailsCreateDto.getSum()) > 0) {
                    throw new ItemAddSellWriteOffException("713", "Сумма скидки(discountSum) не должна быть больше суммы(sum), itemId: " + itemSellDetailsCreateDto.getItemId());
                }
            }
            if (!itemSellDetailsCreateDto.getSum().equals(itemSellDetailsCreateDto.getTotalSum())) {
                throw new ItemAddSellWriteOffException("714", "Если скидки нет, то Итоговая сумма(totalSum) должна быть равна Сумме(sum), itemId: " + itemSellDetailsCreateDto.getItemId());
            }
            if (itemSellDetailsCreateDto.getSumOfDebit() != null) {
                if (!itemSellDetailsCreateDto.isOnDebit()) {
                    throw new ItemAddSellWriteOffException("715", "Если товар не в долг(OnDebit = false) то Суммы в долг(sumOfDebit) не должна быть, itemId: " + itemSellDetailsCreateDto.getItemId());
                }
                if ((itemSellDetailsCreateDto.getSumOfDebit().compareTo(itemSellDetailsCreateDto.getTotalSum())) > 0) {
                    throw new ItemAddSellWriteOffException("716", "Сумма в долг(sumOfDebit) не должна быть больше Итоговой суммы(totalSum), itemId: " + itemSellDetailsCreateDto.getItemId());
                }
                totalSumOfDebit = totalSumOfDebit.add(itemSellDetailsCreateDto.getSumOfDebit());
            }
        }

        if (itemSellDocCreateDto.getDebitDoc() == null) {
            for (ItemSellDetailsCreateDto itemSellDetailsCreateDto : itemSellDocCreateDto.getItemSellDetailsList()) {
                if (itemSellDetailsCreateDto.isOnDebit()) {
                    throw new ItemAddSellWriteOffException("717", "Если товар продан в долг, то DebitDoc обязателен. itemId: " + itemSellDetailsCreateDto.getItemId());
                }
            }
        } else {
            boolean isOnDebitExist = false;
            for (ItemSellDetailsCreateDto itemSellDetailsCreateDto : itemSellDocCreateDto.getItemSellDetailsList()) {
                if (itemSellDetailsCreateDto.isOnDebit()) {
                    isOnDebitExist = true;
                    if (itemSellDetailsCreateDto.getSumOfDebit() == null) {
                        throw new ItemAddSellWriteOffException("718", "Товар в долг, укажите сумму в поле SumOfDebit");
                    }
                    if (itemSellDetailsCreateDto.getSumOfDebit().equals(BigDecimal.ZERO)) {
                        throw new ItemAddSellWriteOffException("719", "Товар в долг, укажите сумму в поле SumOfDebit");
                    }
                }
                if (!isOnDebitExist) {
                    throw new ItemAddSellWriteOffException("720", "Нет товаров продаваемых в долг, DebitDoc должен отсутствовать");
                }
            }

            if (!itemSellDocCreateDto.getDebitDoc().getUserId().equals(itemSellDocCreateDto.getUserId())) {
                throw new ItemAddSellWriteOffException("721", "userId основного документа и userId в DebitDoc должны быть идентичны");
            }
            if (!itemSellDocCreateDto.getDebitDoc().getShopId().equals(itemSellDocCreateDto.getShopId())) {
                throw new ItemAddSellWriteOffException("722", "shopId основного документа и userId в DebitDoc должны быть идентичны");
            }
            if (itemSellDocCreateDto.getDebitDoc().getReturnPlanDate().isBefore(itemSellDocCreateDto.getItemSellDate())) {
                throw new ItemAddSellWriteOffException("723", "Дата возврата долга (returnPlanDate) не должна быть раньше даты продажи(itemSellDate)");
            }
            if ((itemSellDocCreateDto.getDebitDoc().getPartnerId() == null & itemSellDocCreateDto.getPartnerId() != null)
                    || (itemSellDocCreateDto.getDebitDoc().getPartnerId() != null & itemSellDocCreateDto.getPartnerId() == null)) {
                throw new ItemAddSellWriteOffException("724", "Partner основного документа и Partner в DebitDoc должны быть идентичны");
            }
            if (itemSellDocCreateDto.getDebitDoc().getPartnerId() != null & itemSellDocCreateDto.getPartnerId() != null
                    & !itemSellDocCreateDto.getDebitDoc().getPartnerId().equals(itemSellDocCreateDto.getPartnerId())) {
                throw new ItemAddSellWriteOffException("725", "Partner основного документа и Partner в DebitDoc должны быть идентичны");
            }
            if (itemSellDocCreateDto.getDebitDoc().getSumOfDebit() != null) {
                if ((totalSumOfDebit.compareTo(itemSellDocCreateDto.getDebitDoc().getSumOfDebit())) != 0) {
                    throw new ItemAddSellWriteOffException("726", "Сумма долга(sumOfDebit) в DebitDoc должна быть равна общей сумме долга(sumOfDebit) во всех itemSellDetailsDto");
                }
            }
        }
        ItemSellDoc itemSellDoc = itemSellDocCreateConverter.toModel(itemSellDocCreateDto);
        itemSellDocService.persist(itemSellDoc);
        return itemSellDoc;
    }

    @Transactional
    public ItemSellDoc validateAndDeactivate(Long itemSellDocId, Principal principal) {
        if (itemSellDocId == null) {
            throw new ItemAddSellWriteOffException("727", "itemSellDocId не должно быть null");
        }
        if (!itemSellDocService.existsById(itemSellDocId)) {
            throw new ItemAddSellWriteOffException("728", "itemSellDoc с таким id отсутствует, itemSellDocId:" + itemSellDocId);
        }

        ItemSellDoc itemSellDoc = itemSellDocService.findById(itemSellDocId);

//        if (!userService.findByUsername(principal.getName()).equals(itemSellDoc.getUser())) {
//            throw new ItemAddSellWriteOffException("729", "itemSellDoc с таким id отсутствует, itemSellDocId:" + itemSellDocId);
//        }
        if (!itemSellDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("730", "itemSellDoc уже является деактивированным: itemSellDocId:" + itemSellDocId);
        }
        if (itemSellDoc.getDebitDoc() != null) {
            if (itemSellDoc.getDebitDoc().getDebitIncomeDocList() != null) {
                if (itemSellDoc.getDebitDoc().getDebitIncomeDocList().size() > 0) {
                    int numberOfActivateDebitIncomeDocs = 0;
                    for (DebitIncomeDoc debitIncomeDoc : itemSellDoc.getDebitDoc().getDebitIncomeDocList()) {
                        if (debitIncomeDoc.isActivate()) {
                            numberOfActivateDebitIncomeDocs++;
                        }
                    }
                    if (numberOfActivateDebitIncomeDocs > 0) {
                        throw new ItemAddSellWriteOffException("731", "Продажа(itemSellDoc) не может быть аннулирована, так как имеются платежи(DebitIncomeDoc) по долгу (DebitDoc), DebitDocId:"
                                + itemSellDoc.getDebitDoc().getId() + " , пожалуйста аннулируйте платежи по данному долгу");
                    }
                }
            }
        }
        return itemSellDocService.deactivate(itemSellDoc);
    }

    @Transactional
    public ItemSellDoc validateAndChangeOldToNew(Long itemSellDocId, ItemSellDocCreateDto itemSellDocCreateDto, Principal principal) {
        validateAndDeactivate(itemSellDocId, principal);
        return validateAndPersist(itemSellDocCreateDto, principal);
    }
}
