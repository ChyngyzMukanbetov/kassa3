package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalCreditDoc;
import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.CreditDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CreditDocValidation {

    private final CreditDocService creditDocService;
    private final UserService userService;
    private final BalanceService balanceService;

    @Transactional
    public CreditDoc validateReturnPlanDateUpdate(Long creditDocId, LocalDate returnPlanDate, String comment, Principal principal) {
        if (!creditDocService.existsById(creditDocId)) {
            throw new ItemAddSellWriteOffException("901", "CreditDoc с таким id отсутствует, creditDocId: " + creditDocId);
        }
//        if (!creditDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("902", "CreditDoc с таким id отсутствует, creditDocId: " + creditDocId);
//        }
        CreditDoc creditDoc = creditDocService.findById(creditDocId);
        if (!creditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("903", "Долг(CreditDoc) является деактивированным, creditDocId: " + creditDocId);
        }
        if (!creditDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("904", "Долг(CreditDoc) является списанным, creditDocId: " + creditDocId);
        }
        if (returnPlanDate.isBefore(creditDoc.getCreditDate())) {
            throw new ItemAddSellWriteOffException("905", "Дата возврата(returnPlanDate) не может быть ранее даты кредита: " + creditDoc.getCreditDate());
        }
        if (comment.length() > 250) {
            throw new ItemAddSellWriteOffException("906", "Размер коментариев не должен превышать 250 символов, вы ввели: " + comment.length());
        }

        creditDoc.setReturnPlanDate(returnPlanDate);
        creditDoc.setComment(comment);
        creditDocService.update(creditDoc);
        return creditDoc;
    }

    @Transactional
    public CreditDoc validateAndWriteOff(Long creditDocId, LocalDate writeOffDate, String writeOffReason, Principal principal) {
        if (!creditDocService.existsById(creditDocId)) {
            throw new ItemAddSellWriteOffException("907", "CreditDoc с таким id отсутствует, creditDocId: " + creditDocId);
        }
//        if (!creditDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("908", "CreditDoc с таким id отсутствует, creditDocId: " + creditDocId);
//        }
        CreditDoc creditDoc = creditDocService.findById(creditDocId);
        if (!creditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("909", "Долг(CreditDoc) является деактивированным, creditDocId: " + creditDocId);
        }
        if (creditDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("910", "Долг(CreditDoc) является списанным, creditDocId: " + creditDocId);
        }
        if (writeOffDate.isBefore(creditDoc.getCreditDate())) {
            throw new ItemAddSellWriteOffException("911", "Дата списания(writeOffDate) не может быть ранее даты кредита: " + creditDoc.getCreditDate());
        }
        if (writeOffReason.length() > 250) {
            throw new ItemAddSellWriteOffException("912", "Размер причин списания не должен превышать 250 символов, вы ввели: " + writeOffReason.length());
        }

        creditDoc.setWrittenOff(true);
        creditDoc.setWriteOffDate(writeOffDate);
        creditDoc.setWriteOffReason(writeOffReason);
        creditDocService.update(creditDoc);

        Balance balance = balanceService.findByShop(creditDoc.getShop());
        balance.setCreditSum(balance.getCreditSum().subtract(creditDoc.getSumOfCredit()));
        balanceService.update(balance);
        return creditDoc;
    }

    @Transactional
    public CreditDoc validateAndCancelWriteOff(Long creditDocId, Principal principal) {
        if (!creditDocService.existsById(creditDocId)) {
            throw new ItemAddSellWriteOffException("913", "CreditDoc с таким id отсутствует, creditDocId: " + creditDocId);
        }
//        if (!creditDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("914", "CreditDoc с таким id отсутствует, creditDocId: " + creditDocId);
//        }
        CreditDoc creditDoc = creditDocService.findById(creditDocId);
        if (!creditDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("915", "Долг(CreditDoc) является деактивированным, creditDocId: " + creditDocId);
        }
        if (!creditDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("916", "Долг(CreditDoc) неявляется списанным, creditDocId: " + creditDocId);
        }
        creditDoc.setWrittenOff(false);
        creditDoc.setWriteOffDate(null);
        creditDoc.setWriteOffReason(creditDoc.getWriteOffReason().concat("  Списание отменено, дата отмены списания:" + LocalDate.now()));
        creditDocService.update(creditDoc);

        Balance balance = balanceService.findByShop(creditDoc.getShop());
        balance.setCreditSum(balance.getCreditSum().add(creditDoc.getSumOfCredit()));
        balanceService.update(balance);
        return creditDoc;
    }
}
