package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.AdditionalDebitDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalDebitDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalDebitDocResponseDto;
import com.example.kassa3.service.abstracts.document.AdditionalDebitDocService;
import com.example.kassa3.util.validation.dtoDataValidation.AdditionalDebitDocValidation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/additionalDebitDocs")
public class AdditionalDebitDocRestController {

    private final AdditionalDebitDocResponseConverter additionalDebitDocResponseConverter;
    private final AdditionalDebitDocService additionalDebitDocService;
    private final AdditionalDebitDocValidation additionalDebitDocValidation;

    @GetMapping("/getAll")
    public List<AdditionalDebitDocResponseDto> getAllAdditionalDebitDoc() {
        return additionalDebitDocResponseConverter.toDTOList(additionalDebitDocService.findAll());
    }

    @PutMapping("/updateReturnDate/{additionalDebitDocId}")
    public ResponseEntity<? extends Object> updateAdditionalDebitDocReturnDate(
            @PathVariable Long additionalDebitDocId, @RequestParam("returnDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate returnDate,
            @RequestParam("comment") String comment, Principal principal) {
        return new ResponseEntity<>(additionalDebitDocResponseConverter.toDTO(additionalDebitDocValidation.validateReturnPlanDateUpdate(additionalDebitDocId, returnDate, comment, principal)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createAdditionalDebitDoc(@Valid @RequestBody AdditionalDebitDocCreateDto additionalDebitDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
//        additionalDebitDocService.persist(additionalDebitDocCreateConverter.toModel(additionalDebitDocValidation.validate(additionalDebitDocCreateDto, principal)));
        return new ResponseEntity<>(additionalDebitDocResponseConverter.toDTO(additionalDebitDocValidation.validateAndPersist(additionalDebitDocCreateDto, principal)), HttpStatus.OK);
    }

    @PutMapping("/WriteOff/{additionalDebitDocId}")
    public ResponseEntity<? extends Object> writeOffCreditDoc(
            @PathVariable Long additionalDebitDocId, @RequestParam("writeOffDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate writeOffDate,
            @RequestParam("writeOffReason") String writeOffReason, Principal principal) {
        return new ResponseEntity<>(additionalDebitDocResponseConverter.toDTO(additionalDebitDocValidation.validateAndWriteOff(additionalDebitDocId, writeOffDate, writeOffReason, principal)), HttpStatus.OK);
    }

    @PutMapping("/cancelWriteOffCancel/{additionalDebitDocId}")
    public ResponseEntity<? extends Object> cancelWriteOffDebitDoc(
            @PathVariable Long additionalDebitDocId, Principal principal) {
        return new ResponseEntity<>(additionalDebitDocResponseConverter.toDTO(additionalDebitDocValidation.validateAndCancelWriteOff(additionalDebitDocId, principal)), HttpStatus.OK);
    }
}
