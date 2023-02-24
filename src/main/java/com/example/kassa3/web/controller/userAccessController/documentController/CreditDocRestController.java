package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.CreditDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.CreditDocResponseDto;
import com.example.kassa3.service.abstracts.document.CreditDocService;
import com.example.kassa3.util.validation.dtoDataValidation.CreditDocValidation;
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
@RequestMapping("/api/user/creditDocs")
public class CreditDocRestController {

    private final CreditDocResponseConverter creditDocResponseConverter;
    private final CreditDocService creditDocService;
    private final CreditDocValidation creditDocValidation;

    @GetMapping("/getAll")
    public List<CreditDocResponseDto> getAllCreditDoc() {
        return creditDocResponseConverter.toDTOList(creditDocService.findAll());
    }

    @PutMapping("/updateReturnDate/{creditDocId}")
    public ResponseEntity<? extends Object> updateCreditDocReturnDate(
            @PathVariable Long creditDocId, @RequestParam("returnDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate returnDate,
            @RequestParam("comment") String comment, Principal principal) {
        return new ResponseEntity<>(creditDocResponseConverter.toDTO(creditDocValidation.validateReturnPlanDateUpdate(creditDocId, returnDate, comment, principal)), HttpStatus.OK);
    }

    @PutMapping("/WriteOff/{creditDocId}")
    public ResponseEntity<? extends Object> writeOffCreditDoc(
            @PathVariable Long creditDocId, @RequestParam("writeOffDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate writeOffDate,
            @RequestParam("writeOffReason") String writeOffReason, Principal principal) {
        return new ResponseEntity<>(creditDocResponseConverter.toDTO(creditDocValidation.validateAndWriteOff(creditDocId, writeOffDate, writeOffReason, principal)), HttpStatus.OK);
    }

    @PutMapping("/cancelWriteOffCancel/{creditDocId}")
    public ResponseEntity<? extends Object> cancelWriteOffCreditDoc(
            @PathVariable Long creditDocId, Principal principal) {
        return new ResponseEntity<>(creditDocResponseConverter.toDTO(creditDocValidation.validateAndCancelWriteOff(creditDocId, principal)), HttpStatus.OK);
    }
}
