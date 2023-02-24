package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.DebitDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.DebitDocResponseDto;
import com.example.kassa3.service.abstracts.document.DebitDocService;
import com.example.kassa3.util.validation.dtoDataValidation.DebitDocValidation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/debitDocs")
public class DebitDocRestController {

    private final DebitDocResponseConverter debitDocResponseConverter;
    private final DebitDocService debitDocService;
    private final DebitDocValidation debitDocValidation;

    @GetMapping("/getAll")
    public List<DebitDocResponseDto> getAllDebitDoc() {
        return debitDocResponseConverter.toDTOList(debitDocService.findAll());
    }

    @PutMapping("/updateReturnDate/{debitDocId}")
    public ResponseEntity<? extends Object> updateDebitDocReturnDate(
            @PathVariable Long debitDocId, @RequestParam("returnDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate returnDate,
            @RequestParam("comment") String comment, Principal principal) {
        return new ResponseEntity<>(debitDocResponseConverter.toDTO(debitDocValidation.validateReturnPlanDateUpdate(debitDocId, returnDate, comment, principal)), HttpStatus.OK);
    }

    @PutMapping("/WriteOff/{debitDocId}")
    public ResponseEntity<? extends Object> writeOffCreditDoc(
            @PathVariable Long debitDocId, @RequestParam("writeOffDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate writeOffDate,
            @RequestParam("writeOffReason") String writeOffReason, Principal principal) {
        return new ResponseEntity<>(debitDocResponseConverter.toDTO(debitDocValidation.validateAndWriteOff(debitDocId, writeOffDate, writeOffReason, principal)), HttpStatus.OK);
    }

    @PutMapping("/cancelWriteOffCancel/{debitDocId}")
    public ResponseEntity<? extends Object> cancelWriteOffDebitDoc(
            @PathVariable Long debitDocId, Principal principal) {
        return new ResponseEntity<>(debitDocResponseConverter.toDTO(debitDocValidation.validateAndCancelWriteOff(debitDocId, principal)), HttpStatus.OK);
    }
}
