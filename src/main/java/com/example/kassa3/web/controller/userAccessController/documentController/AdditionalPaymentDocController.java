package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.AdditionalPaymentDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.AdditionalPaymentDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.AdditionalPaymentDocResponseDto;
import com.example.kassa3.service.abstracts.document.AdditionalPaymentDocService;
import com.example.kassa3.util.validation.dtoDataValidation.AdditionalPaymentDocValidation;
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
@RequestMapping("/api/user/additionalPaymentDocs")
public class AdditionalPaymentDocController {

    private final AdditionalPaymentDocService additionalPaymentDocService;
    private  final AdditionalPaymentDocValidation additionalPaymentDocValidation;
    private final AdditionalPaymentDocResponseConverter additionalPaymentDocResponseConverter;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createAdditionalPaymentDoc(@Valid @RequestBody AdditionalPaymentDocCreateDto additionalPaymentDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(additionalPaymentDocResponseConverter.toDTO(additionalPaymentDocValidation.validateAndPersist(additionalPaymentDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<AdditionalPaymentDocResponseDto> getAllAdditionalPaymentDoc() {
        return additionalPaymentDocResponseConverter.toDTOList(additionalPaymentDocService.findAll());
    }

    @PutMapping("/deactivate/{additionalPaymentDocId}")
    public ResponseEntity<? extends Object> deactivateAdditionalPaymentDoc(@PathVariable Long additionalPaymentDocId, Principal principal) {
        return new ResponseEntity<>(additionalPaymentDocResponseConverter.toDTO(additionalPaymentDocValidation.validateAndDeactivate(additionalPaymentDocId, principal)), HttpStatus.OK);
    }
}
