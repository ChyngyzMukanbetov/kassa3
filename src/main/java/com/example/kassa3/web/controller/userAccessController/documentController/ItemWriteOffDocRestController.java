package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.ItemWriteOffDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemWriteOffDocResponseDto;
import com.example.kassa3.service.abstracts.document.ItemWriteOffDocService;
import com.example.kassa3.util.validation.dtoDataValidation.ItemWriteOffDocValidation;
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
@RequestMapping("/api/user/itemWriteOffDocs")
public class ItemWriteOffDocRestController {

    private final ItemWriteOffDocService itemWriteOffDocService;
    private final ItemWriteOffDocResponseConverter itemWriteOffDocResponseConverter;
    private final ItemWriteOffDocValidation itemWriteOffDocValidation;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createItemWriteOffDoc(@Valid @RequestBody ItemWriteOffDocCreateDto itemWriteOffDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemWriteOffDocResponseConverter.toDTO(itemWriteOffDocValidation.validateAndPersist(itemWriteOffDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<ItemWriteOffDocResponseDto> getAllItemWriteOffDoc() {
        return itemWriteOffDocResponseConverter.toDTOList(itemWriteOffDocService.findAll());
    }

    @PutMapping("/deactivate/{itemWriteOffDocId}")
    public ResponseEntity<? extends Object> deactivateItemWriteOffDoc(@PathVariable Long itemWriteOffDocId, Principal principal) {
        return new ResponseEntity<>(itemWriteOffDocResponseConverter.toDTO(itemWriteOffDocValidation.validateAndDeactivate(itemWriteOffDocId, principal)), HttpStatus.OK);
    }
}
