package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.CreditPaymentDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.CreditPaymentDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.CreditPaymentDocResponseDto;
import com.example.kassa3.service.abstracts.document.CreditPaymentDocService;
import com.example.kassa3.util.validation.dtoDataValidation.CreditPaymentDocValidation;
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
@RequestMapping("/api/user/creditPaymentDocs")
public class CreditPaymentDocRestController {

    private final CreditPaymentDocService creditPaymentDocService;
    private  final CreditPaymentDocValidation creditPaymentDocValidation;
    private final CreditPaymentDocResponseConverter creditPaymentDocResponseConverter;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createCreditPaymentDoc(@Valid @RequestBody CreditPaymentDocCreateDto creditPaymentDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(creditPaymentDocResponseConverter.toDTO(creditPaymentDocValidation.validateAndPersist(creditPaymentDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<CreditPaymentDocResponseDto> getAllCreditPaymentDoc() {
        return creditPaymentDocResponseConverter.toDTOList(creditPaymentDocService.findAll());
    }

    @PutMapping("/deactivate/{creditPaymentDocId}")
    public ResponseEntity<? extends Object> deactivateCreditPaymentDoc(@PathVariable Long creditPaymentDocId, Principal principal) {
        return new ResponseEntity<>(creditPaymentDocResponseConverter.toDTO(creditPaymentDocValidation.validateAndDeactivate(creditPaymentDocId, principal)), HttpStatus.OK);
    }
}
