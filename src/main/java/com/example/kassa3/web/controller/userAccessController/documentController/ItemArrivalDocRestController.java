package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.ItemArrivalDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemArrivalDocResponseDto;
import com.example.kassa3.service.abstracts.document.ItemArrivalDocService;
import com.example.kassa3.util.validation.dtoDataValidation.ItemArrivalDocValidation;
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
@RequestMapping("/api/user/itemArrivalDocs")
public class ItemArrivalDocRestController {

    private final ItemArrivalDocService itemArrivalDocService;
    private final ItemArrivalDocResponseConverter itemArrivalDocResponseConverter;
    private final ItemArrivalDocValidation itemArrivalDocValidation;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createItemArrivalDoc(@Valid @RequestBody ItemArrivalDocCreateDto itemArrivalDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemArrivalDocResponseConverter.toDTO(itemArrivalDocValidation.validateAndPersist(itemArrivalDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<ItemArrivalDocResponseDto> getAllItemArrivalDoc() {
        return itemArrivalDocResponseConverter.toDTOList(itemArrivalDocService.findAll());
    }

    @PutMapping("/deactivate/{itemArrivalDocId}")
    public ResponseEntity<? extends Object> deactivateItemArrivalDoc(@PathVariable Long itemArrivalDocId, Principal principal) {
        return new ResponseEntity<>(itemArrivalDocResponseConverter.toDTO(itemArrivalDocValidation.validateAndDeactivate(itemArrivalDocId, principal)), HttpStatus.OK);
    }
}
