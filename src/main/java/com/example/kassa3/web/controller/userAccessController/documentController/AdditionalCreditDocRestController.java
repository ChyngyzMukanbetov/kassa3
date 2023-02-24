package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.AdditionalCreditDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalCreditDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalCreditDocResponseDto;
import com.example.kassa3.service.abstracts.document.AdditionalCreditDocService;
import com.example.kassa3.util.validation.dtoDataValidation.AdditionalCreditDocValidation;
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
@RequestMapping("/api/user/additionalCreditDocs")
public class AdditionalCreditDocRestController {

    private final AdditionalCreditDocResponseConverter additionalCreditDocResponseConverter;
    private final AdditionalCreditDocService additionalCreditDocService;
    private final AdditionalCreditDocValidation additionalCreditDocValidation;

    @GetMapping("/getAll")
    public List<AdditionalCreditDocResponseDto> getAllAdditionalCreditDoc() {
        return additionalCreditDocResponseConverter.toDTOList(additionalCreditDocService.findAll());
    }

    @PutMapping("/updateReturnDate/{additionalCreditDocId}")
    public ResponseEntity<? extends Object> updateAdditionalCreditDocReturnDate(
            @PathVariable Long additionalCreditDocId, @RequestParam("returnDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate returnDate,
            @RequestParam("comment") String comment, Principal principal) {
        return new ResponseEntity<>(additionalCreditDocResponseConverter.toDTO(additionalCreditDocValidation.validateReturnPlanDateUpdate(additionalCreditDocId, returnDate, comment, principal)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createAdditionalCreditDoc(@Valid @RequestBody AdditionalCreditDocCreateDto additionalCreditDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
//        additionalCreditDocValidation.validateAndPersist(additionalCreditDocCreateDto, principal);
        return new ResponseEntity<>(additionalCreditDocResponseConverter.toDTO(additionalCreditDocValidation.validateAndPersist(additionalCreditDocCreateDto, principal)), HttpStatus.OK);
//        return new ResponseEntity<>("Кредит не связанный с оприходованием товара успешно создан", HttpStatus.OK);
    }

    @PutMapping("/writeOff/{additionalCreditDocId}")
    public ResponseEntity<? extends Object> writeOffAdditionalCreditDoc(
            @PathVariable Long additionalCreditDocId, @RequestParam("writeOffDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate writeOffDate,
            @RequestParam("writeOffReason") String writeOffReason, Principal principal) {
        return new ResponseEntity<>(additionalCreditDocResponseConverter.toDTO(additionalCreditDocValidation.validateAndWriteOff(additionalCreditDocId, writeOffDate, writeOffReason, principal)), HttpStatus.OK);
    }

    @PutMapping("/cancelWriteOff/{additionalCreditDocId}")
    public ResponseEntity<? extends Object> cancelWriteOffAdditionalCreditDoc(
            @PathVariable Long additionalCreditDocId, Principal principal) {
        return new ResponseEntity<>(additionalCreditDocResponseConverter.toDTO(additionalCreditDocValidation.validateAndCancelWriteOff(additionalCreditDocId, principal)), HttpStatus.OK);
    }
}
