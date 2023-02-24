package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.ItemSellDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemSellDocResponseDto;
import com.example.kassa3.service.abstracts.document.ItemSellDocService;
import com.example.kassa3.util.validation.dtoDataValidation.ItemSellDocValidation;
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
@RequestMapping("/api/user/itemSellDocs")
public class ItemSellDocRestController {

    private final ItemSellDocService itemSellDocService;
    private final ItemSellDocResponseConverter itemSellDocResponseConverter;
    private final ItemSellDocValidation itemSellDocValidation;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createItemSellDoc(@Valid @RequestBody ItemSellDocCreateDto itemSellDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemSellDocResponseConverter.toDTO(itemSellDocValidation.validateAndPersist(itemSellDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<ItemSellDocResponseDto> getAllItemSellDoc() {
        return itemSellDocResponseConverter.toDTOList(itemSellDocService.findAll());
    }

    @PutMapping("/deactivate/{itemSellDocId}")
    public ResponseEntity<? extends Object> deactivateItemSellDoc(@PathVariable Long itemSellDocId, Principal principal) {
        return new ResponseEntity<>(itemSellDocResponseConverter.toDTO(itemSellDocValidation.validateAndDeactivate(itemSellDocId, principal)), HttpStatus.OK);
    }
}
