package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.InventorizationDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.InventorizationDocResponseDto;
import com.example.kassa3.service.abstracts.document.InventorizationDocService;
import com.example.kassa3.util.validation.dtoDataValidation.InventorizationDocValidation;
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
@RequestMapping("/api/user/inventorizationDocs")
public class InventorizationDocRestController {

    private final InventorizationDocService inventorizationDocService;
    private final InventorizationDocResponseConverter inventorizationDocResponseConverter;
    private final InventorizationDocValidation inventorizationDocValidation;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createInventorizationDoc(@Valid @RequestBody InventorizationDocCreateDto inventorizationDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(inventorizationDocResponseConverter.toDTO(inventorizationDocValidation.validateAndPersist(inventorizationDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<InventorizationDocResponseDto> getAllInventorizationDoc() {
        return inventorizationDocResponseConverter.toDTOList(inventorizationDocService.findAll());
    }
}
