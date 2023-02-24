package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.AdditionalDebitDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalDebitDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalDebitDocCreateDto;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.AdditionalDebitDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AdditionalDebitDocValidation {

    private final AdditionalDebitDocService additionalDebitDocService;
    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final AdditionalDebitDocCreateConverter additionalDebitDocCreateConverter;
    private final BalanceService balanceService;

    @Transactional
    public AdditionalDebitDoc validateAndPersist(AdditionalDebitDocCreateDto additionalDebitDocCreateDto, Principal principal) {

        if (!userService.existsById(additionalDebitDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1501", "User с таким id отсутствует, userId:" + additionalDebitDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(additionalDebitDocCreateDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1502", "Вы указали не Ваш userId в поле additionalDebitDocCreateDto.userId");
//        }

        if (!shopService.existsById(additionalDebitDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1503", "Shop с таким id отсутствует: shopId:" + additionalDebitDocCreateDto.getShopId());
        }

//        if (!shopService.findById(additionalDebitDocCreateDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1504", "Shop с таким id отсутствует:" + additionalDebitDocCreateDto.getShopId());
//        }

        if (additionalDebitDocCreateDto.getReturnPlanDate().isBefore(additionalDebitDocCreateDto.getDebitDate())) {
            throw new ItemAddSellWriteOffException("1505", "Дата возврата(returnPlanDate) не может быть ранее даты кредита: " + additionalDebitDocCreateDto.getDebitDate());
        }
        if (!partnerService.existsById(additionalDebitDocCreateDto.getPartnerId())) {
            throw new ItemAddSellWriteOffException("1506", "Partner с таким id отсутствует, partnerId:" + additionalDebitDocCreateDto.getPartnerId());
//        } else if (!partnerService.findById(additionalDebitDocCreateDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//        throw new ItemAddSellWriteOffException("1507", "Partner с таким id отсутствует:" + additionalDebitDocCreateDto.getPartnerId());
        }
        AdditionalDebitDoc additionalDebitDoc = additionalDebitDocCreateConverter.toModel(additionalDebitDocCreateDto);
        additionalDebitDocService.persist(additionalDebitDoc);
        return additionalDebitDoc;
    }

    @Transactional
    public AdditionalDebitDoc validateReturnPlanDateUpdate(Long additionalDebitDocId, LocalDate returnPlanDate, String comment, Principal principal) {
        if (!additionalDebitDocService.existsById(additionalDebitDocId)) {
            throw new ItemAddSellWriteOffException("1508", "AdditionalDebitDoc с таким id отсутствует, additionalDebitDocId: " + additionalDebitDocId);
        }
        AdditionalDebitDoc additionalDebitDoc = additionalDebitDocService.findById(additionalDebitDocId);
//        if (!additionalDebitDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1509", "AdditionalDebitDoc с таким id отсутствует, additionalDebitDocId: " + additionalDebitDocId);
//        }
        if (!additionalDebitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1510", "AdditionalDebitDoc является деактивированным, additionalDebitDocId: " + additionalDebitDocId);
        }
        if (returnPlanDate.isBefore(additionalDebitDoc.getDebitDate())) {
            throw new ItemAddSellWriteOffException("1511", "Дата возврата(returnPlanDate) не может быть ранее даты долга: " + additionalDebitDoc.getDebitDate());
        }
        if (comment.length() > 250) {
            throw new ItemAddSellWriteOffException("1512", "Размер коментариев не должен превышать 250 символов, вы ввели: " + comment.length());
        }

        additionalDebitDoc.setReturnPlanDate(returnPlanDate);
        additionalDebitDoc.setComment(comment);
        additionalDebitDocService.update(additionalDebitDoc);
        return additionalDebitDoc;
    }

    @Transactional
    public AdditionalDebitDoc validateAndWriteOff(Long additionalDebitDocId, LocalDate writeOffDate, String writeOffReason, Principal principal) {
        if (!additionalDebitDocService.existsById(additionalDebitDocId)) {
            throw new ItemAddSellWriteOffException("1513", "DebitDoc с таким id отсутствует, debitDocId: " + additionalDebitDocId);
        }
//        if (!debitDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1514", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
//        }
        AdditionalDebitDoc additionalDebitDoc = additionalDebitDocService.findById(additionalDebitDocId);
        if (!additionalDebitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1515", "Долг(AdditionalDebitDoc) является деактивированным, additionalDebitDocId: " + additionalDebitDocId);
        }
        if (additionalDebitDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("1516", "Долг(AdditionalDebitDoc) является списанным, additionalDebitDocId: " + additionalDebitDocId);
        }
        if (writeOffDate.isBefore(additionalDebitDoc.getDebitDate())) {
            throw new ItemAddSellWriteOffException("1517", "Дата списания(writeOffDate) не может быть ранее даты кредита: " + additionalDebitDoc.getDebitDate());
        }
        if (writeOffReason.length() > 250) {
            throw new ItemAddSellWriteOffException("1518", "Размер причин списания не должен превышать 250 символов, вы ввели: " + writeOffReason.length());
        }

        additionalDebitDoc.setWrittenOff(true);
        additionalDebitDoc.setWriteOffDate(writeOffDate);
        additionalDebitDoc.setWriteOffReason(writeOffReason);
        additionalDebitDocService.update(additionalDebitDoc);

        Balance balance = balanceService.findByShop(additionalDebitDoc.getShop());
        balance.setDebitSum(balance.getDebitSum().subtract(additionalDebitDoc.getSumOfDebit()));
        balanceService.update(balance);
        return additionalDebitDoc;
    }

    @Transactional
    public AdditionalDebitDoc validateAndCancelWriteOff(Long additionalDebitDocId, Principal principal) {
        if (!additionalDebitDocService.existsById(additionalDebitDocId)) {
            throw new ItemAddSellWriteOffException("1519", "AdditionalDebitDoc с таким id отсутствует, additionalDebitDocId: " + additionalDebitDocId);
        }
//        if (!additionalDebitDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1520", "AdditionalDebitDoc с таким id отсутствует, additionalDebitDocId: " + additionalDebitDocId);
//        }
        AdditionalDebitDoc additionalDebitDoc = additionalDebitDocService.findById(additionalDebitDocId);
        if (!additionalDebitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1521", "Долг(AdditionalDebitDoc) является деактивированным, additionalDebitDocId: " + additionalDebitDocId);
        }
        if (!additionalDebitDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("1522", "Долг(AdditionalDebitDoc) неявляется списанным, additionalDebitDocId: " + additionalDebitDocId);
        }
        additionalDebitDoc.setWrittenOff(false);
        additionalDebitDoc.setWriteOffDate(null);
        additionalDebitDoc.setWriteOffReason(additionalDebitDoc.getWriteOffReason().concat("  Списание отменено, дата отмены списания:" + LocalDate.now()));
        additionalDebitDocService.update(additionalDebitDoc);

        Balance balance = balanceService.findByShop(additionalDebitDoc.getShop());
        balance.setDebitSum(balance.getDebitSum().add(additionalDebitDoc.getSumOfDebit()));
        balanceService.update(balance);
        return additionalDebitDoc;
    }
}
