package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.DebitIncomeDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.DebitIncomeDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.DebitIncomeDocResponseDto;
import com.example.kassa3.service.abstracts.document.DebitIncomeDocService;
import com.example.kassa3.util.validation.dtoDataValidation.DebitIncomeDocValidation;
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
@RequestMapping("/api/user/debitIncomeDocs")
public class DebitIncomeDocRestController {

    private final DebitIncomeDocService debitIncomeDocService;
    private  final DebitIncomeDocValidation debitIncomeDocValidation;
    private final DebitIncomeDocResponseConverter debitIncomeDocResponseConverter;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createDebitIncomeDoc(@Valid @RequestBody DebitIncomeDocCreateDto debitIncomeDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(debitIncomeDocResponseConverter.toDTO(debitIncomeDocValidation.validateAndPersist(debitIncomeDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<DebitIncomeDocResponseDto> getAllDebitIncomeDoc() {
        return debitIncomeDocResponseConverter.toDTOList(debitIncomeDocService.findAll());
    }

    @PutMapping("/deactivate/{debitIncomeDocId}")
    public ResponseEntity<? extends Object> deactivateDebitIncomeDoc(@PathVariable Long debitIncomeDocId, Principal principal) {
        return new ResponseEntity<>(debitIncomeDocResponseConverter.toDTO(debitIncomeDocValidation.validateAndDeactivate(debitIncomeDocId, principal)), HttpStatus.OK);
    }
}
