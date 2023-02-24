package com.example.kassa3.util.validation.dtoDataValidation;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.example.kassa3.model.document.AdditionalDebitDoc;
import com.example.kassa3.model.document.CreditDoc;
import com.example.kassa3.model.document.DebitDoc;
import com.example.kassa3.model.entity.Balance;
import com.example.kassa3.service.abstracts.BalanceService;
import com.example.kassa3.service.abstracts.UserService;
import com.example.kassa3.service.abstracts.document.DebitDocService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class DebitDocValidation {

    private final DebitDocService debitDocService;
    private final UserService userService;
    private final BalanceService balanceService;

    @Transactional
    public DebitDoc validateReturnPlanDateUpdate(Long debitDocId, LocalDate returnPlanDate, String comment, Principal principal) {
        if (!debitDocService.existsById(debitDocId)) {
            throw new ItemAddSellWriteOffException("1001", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
        }
        DebitDoc debitDoc = debitDocService.findById(debitDocId);
//        if (!debitDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1002", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
//        }
        if (!debitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1003", "DebitDoc является деактивированным, debitDocId: " + debitDocId);
        }
        if (returnPlanDate.isBefore(debitDoc.getDebitDate())) {
            throw new ItemAddSellWriteOffException("1004", "Дата возврата(returnPlanDate) не может быть ранее даты кредита: " + debitDoc.getDebitDate());
        }
        if (comment.length() > 250) {
            throw new ItemAddSellWriteOffException("1005", "Размер коментариев не должен превышать 250 символов, вы ввели: " + comment.length());
        }


        debitDoc.setReturnPlanDate(returnPlanDate);
        debitDoc.setComment(comment);
        debitDocService.update(debitDoc);
        return debitDoc;
    }

    @Transactional
    public DebitDoc validateAndWriteOff(Long debitDocId, LocalDate writeOffDate, String writeOffReason, Principal principal) {
        if (!debitDocService.existsById(debitDocId)) {
            throw new ItemAddSellWriteOffException("1006", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
        }
//        if (!debitDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1007", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
//        }
        DebitDoc debitDoc = debitDocService.findById(debitDocId);
        if (!debitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1008", "Долг(DebitDoc) является деактивированным, debitDocId: " + debitDocId);
        }
        if (debitDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("1009", "Долг(DebitDoc) является списанным, debitDocId: " + debitDocId);
        }
        if (writeOffDate.isBefore(debitDoc.getDebitDate())) {
            throw new ItemAddSellWriteOffException("1010", "Дата списания(writeOffDate) не может быть ранее даты кредита: " + debitDoc.getDebitDate());
        }
        if (writeOffReason.length() > 250) {
            throw new ItemAddSellWriteOffException("1011", "Размер причин списания не должен превышать 250 символов, вы ввели: " + writeOffReason.length());
        }

        debitDoc.setWrittenOff(true);
        debitDoc.setWriteOffDate(writeOffDate);
        debitDoc.setWriteOffReason(writeOffReason);
        debitDocService.update(debitDoc);

        Balance balance = balanceService.findByShop(debitDoc.getShop());
        balance.setDebitSum(balance.getDebitSum().subtract(debitDoc.getSumOfDebit()));
        balanceService.update(balance);
        return debitDoc;
    }

    @Transactional
    public DebitDoc validateAndCancelWriteOff(Long debitDocId, Principal principal) {
        if (!debitDocService.existsById(debitDocId)) {
            throw new ItemAddSellWriteOffException("1012", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
        }
//        if (!debitDoc.getUser().equals(userService.findByUsername(principal.getName()))) {
//            throw new ItemAddSellWriteOffException("1013", "DebitDoc с таким id отсутствует, debitDocId: " + debitDocId);
//        }
        DebitDoc debitDoc = debitDocService.findById(debitDocId);
        if (!debitDoc.isActivate()) {
            throw new ItemAddSellWriteOffException("1014", "Долг(DebitDoc) является деактивированным, debitDocId: " + debitDocId);
        }
        if (!debitDoc.isWrittenOff()) {
            throw new ItemAddSellWriteOffException("1015", "Долг(DebitDoc) неявляется списанным, debitDocId: " + debitDocId);
        }
        debitDoc.setWrittenOff(false);
        debitDoc.setWriteOffDate(null);
        debitDoc.setWriteOffReason(debitDoc.getWriteOffReason().concat("  Списание отменено, дата отмены списания:" + LocalDate.now()));
        debitDocService.update(debitDoc);

        Balance balance = balanceService.findByShop(debitDoc.getShop());
        balance.setDebitSum(balance.getDebitSum().add(debitDoc.getSumOfDebit()));
        balanceService.update(balance);
        return debitDoc;
    }
}
