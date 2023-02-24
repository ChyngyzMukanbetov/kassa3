package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.converter.documentConverter.documentCreateConverter.AdditionalCreditDocCreateConverter;
import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalCreditDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalCreditDocCreateDto;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.ShopService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.AdditionalCreditDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AdditionalCreditDocValidation {
    private final AdditionalCreditDocService additionalCreditDocService;
    private final UserService userService;
    private final ShopService shopService;
    private final PartnerService partnerService;
    private final AdditionalCreditDocCreateConverter additionalCreditDocCreateConverter;
    private final BalanceService balanceService;

    @Transactional
    public AdditionalCreditDoc validateAndPersist(AdditionalCreditDocCreateDto additionalCreditDocCreateDto, Principal principal) {

        if (!userService.existsById(additionalCreditDocCreateDto.getUserId())) {
            throw new ItemAddSellWriteOffException("1401", "User с таким id отсутствует, userId:" + additionalCreditDocCreateDto.getUserId());
        }

//        if (!userService.findByUsername(principal.getName()).getId().equals(additionalCreditDocCreateDto.getUserId())) {
//            throw new ItemAddSellWriteOffException("1402", "Вы указали не Ваш userId в поле additionalCreditDocCreateDto.userId");
//        }

        if (!shopService.existsById(additionalCreditDocCreateDto.getShopId())) {
            throw new ItemAddSellWriteOffException("1403", "Shop с таким id отсутствует: shopId:" + additionalCreditDocCreateDto.getShopId());
        }

//        if (!shopService.findById(additionalCreditDocCreateDto.getShopId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1404", "Shop с таким id отсутствует:" + additionalCreditDocCreateDto.getShopId());
//        }

        if (additionalCreditDocCreateDto.getReturnPlanDate().isBefore(additionalCreditDocCreateDto.getCreditDate())) {
            throw new ItemAddSellWriteOffException("1405", "Дата возврата(returnPlanDate) не может быть ранее даты кредита: " + additionalCreditDocCreateDto.getCreditDate());
        }
        if (!partnerService.existsById(additionalCreditDocCreateDto.getPartnerId())) {
            throw new ItemAddSellWriteOffException("1406", "Partner с таким id отсутствует, partnerId:" + additionalCreditDocCreateDto.getPartnerId());
//        } else if (!partnerService.findById(additionalCreditDocCreateDto.getPartnerId()).getUser().equals(userService.findByUsername(principal.getName()))) {
//        throw new ItemAddSellWriteOffException("1407", "Partner с таким id отсутствует:" + additionalCreditDocCreateDto.getPartnerId());
        }
        AdditionalCreditDoc additionalCreditDoc = additionalCreditDocCreateConverter.toModel(additionalCreditDocCreateDto);
        additionalCreditDocService.persist(additionalCreditDoc);
        return additionalCreditDoc;
    }

    @Transactional
    public AdditionalCreditDoc validateReturnPlanDateUpdate(Long additionalCreditDocId, LocalDate returnPlanDate, String comment, Principal principal) {
        if (!additionalCreditDocService.existsById(additionalCreditDocId)) {
            throw new ItemAddSellWriteOffException("1408", "AdditionalCreditDoc с таким id отсутствует, additionalCreditDocId: " + additionalCreditDocId);
        }
        AdditionalCreditDoc additionalCreditDoc = additionalCreditDocService.findById(additionalCreditDocId);
//        if (!additionalCreditDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1409", "AdditionalCreditDoc с таким id отсутствует, additionalCreditDocId: " + additionalCreditDocId);
//        }
        if (!additionalCreditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1410", "AdditionalCreditDoc является деактивированным, additionalCreditDocId: " + additionalCreditDocId);
        }
        if (returnPlanDate.isBefore(additionalCreditDoc.getCreditDate())) {
            throw new ItemAddSellWriteOffException("1411", "Дата возврата(returnPlanDate) не может быть ранее даты кредита: " + additionalCreditDoc.getCreditDate());
        }
        if (comment.length() > 250) {
            throw new ItemAddSellWriteOffException("1412", "Размер коментариев не должен превышать 250 символов, вы ввели: " + comment.length());
        }

        additionalCreditDoc.setReturnPlanDate(returnPlanDate);
        additionalCreditDoc.setComment(comment);
        additionalCreditDocService.update(additionalCreditDoc);
        return additionalCreditDoc;
    }

    @Transactional
    public AdditionalCreditDoc validateAndWriteOff(Long additionalCreditDocId, LocalDate writeOffDate, String writeOffReason, Principal principal) {
        if (!additionalCreditDocService.existsById(additionalCreditDocId)) {
            throw new ItemAddSellWriteOffException("1413", "AdditionalCreditDoc с таким id отсутствует, additionalCreditDocId: " + additionalCreditDocId);
        }
//        if (!additionalCreditDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1414", "AdditionalCreditDoc с таким id отсутствует, additionalCreditDocId: " + additionalCreditDocId);
//        }
        AdditionalCreditDoc additionalCreditDoc = additionalCreditDocService.findById(additionalCreditDocId);
        if (!additionalCreditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1415", "Долг(AdditionalCreditDoc) является деактивированным, additionalCreditDocId: " + additionalCreditDocId);
        }
        if (additionalCreditDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("1416", "Долг(AdditionalCreditDoc) является списанным, additionalCreditDocId: " + additionalCreditDocId);
        }
        if (writeOffDate.isBefore(additionalCreditDoc.getCreditDate())) {
            throw new ItemAddSellWriteOffException("1417", "Дата списания(writeOffDate) не может быть ранее даты кредита: " + additionalCreditDoc.getCreditDate());
        }
        if (writeOffReason.length() > 250) {
            throw new ItemAddSellWriteOffException("1418", "Размер причин списания не должен превышать 250 символов, вы ввели: " + writeOffReason.length());
        }

        additionalCreditDoc.setWrittenOff(true);
        additionalCreditDoc.setWriteOffDate(writeOffDate);
        additionalCreditDoc.setWriteOffReason(writeOffReason);
        additionalCreditDocService.update(additionalCreditDoc);

        Balance balance = balanceService.findByShop(additionalCreditDoc.getShop());
        balance.setCreditSum(balance.getCreditSum().subtract(additionalCreditDoc.getSumOfCredit()));
        balanceService.update(balance);
        return additionalCreditDoc;
    }

    @Transactional
    public AdditionalCreditDoc validateAndCancelWriteOff(Long additionalCreditDocId, Principal principal) {
        if (!additionalCreditDocService.existsById(additionalCreditDocId)) {
            throw new ItemAddSellWriteOffException("1419", "AdditionalCreditDoc с таким id отсутствует, additionalCreditDocId: " + additionalCreditDocId);
        }
//        if (!additionalCreditDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1420", "AdditionalCreditDoc с таким id отсутствует, additionalCreditDocId: " + additionalCreditDocId);
//        }
        AdditionalCreditDoc additionalCreditDoc = additionalCreditDocService.findById(additionalCreditDocId);
        if (!additionalCreditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1421", "Долг(AdditionalCreditDoc) является деактивированным, additionalCreditDocId: " + additionalCreditDocId);
        }
        if (!additionalCreditDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("1422", "Долг(AdditionalCreditDoc) неявляется списанным, additionalCreditDocId: " + additionalCreditDocId);
        }
        additionalCreditDoc.setWrittenOff(false);
        additionalCreditDoc.setWriteOffDate(null);
        additionalCreditDoc.setWriteOffReason(additionalCreditDoc.getWriteOffReason().concat("  Списание отменено, дата отмены списания:" + LocalDate.now()));
        additionalCreditDocService.update(additionalCreditDoc);

        Balance balance = balanceService.findByShop(additionalCreditDoc.getShop());
        balance.setCreditSum(balance.getCreditSum().add(additionalCreditDoc.getSumOfCredit()));
        balanceService.update(balance);
        return additionalCreditDoc;
    }
}
