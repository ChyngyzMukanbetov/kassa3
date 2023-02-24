package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.AdditionalIncomeDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalCreditDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalIncomeDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalIncomeDocResponseDto;
import com.example.kassa3.service.abstracts.document.AdditionalIncomeDocService;
import com.example.kassa3.util.validation.dtoDataValidation.AdditionalIncomeDocValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/additionalIncomeDocs")
public class AdditionalIncomeDocController {

    private final AdditionalIncomeDocService additionalIncomeDocService;
    private  final AdditionalIncomeDocValidation additionalIncomeDocValidation;
    private final AdditionalIncomeDocResponseConverter additionalIncomeDocResponseConverter;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createAdditionalIncomeDoc(@Valid @RequestBody AdditionalIncomeDocCreateDto additionalIncomeDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(additionalIncomeDocResponseConverter.toDTO(additionalIncomeDocValidation.validateAndPersist(additionalIncomeDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<AdditionalIncomeDocResponseDto> getAllAdditionalIncomeDoc() {
        return additionalIncomeDocResponseConverter.toDTOList(additionalIncomeDocService.findAll());
    }

    @PutMapping("/deactivate/{additionalIncomeDocId}")
    public ResponseEntity<? extends Object> deactivateAdditionalIncomeDoc(@PathVariable Long additionalIncomeDocId, Principal principal) {
        return new ResponseEntity<>(additionalIncomeDocResponseConverter.toDTO(additionalIncomeDocValidation.validateAndDeactivate(additionalIncomeDocId, principal)), HttpStatus.OK);
    }

    @PutMapping("/changeOldToNew/{additionalIncomeDocId}")
    public ResponseEntity<? extends Object> changeOldToNewAdditionalIncomeDoc(
            @Valid @RequestBody AdditionalIncomeDocCreateDto additionalIncomeDocCreateDto, BindingResult result, @PathVariable Long additionalIncomeDocId, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(additionalIncomeDocResponseConverter.toDTO(additionalIncomeDocValidation.validateAndChangeOldToNew(additionalIncomeDocId, additionalIncomeDocCreateDto, principal)), HttpStatus.OK);
    }
}
