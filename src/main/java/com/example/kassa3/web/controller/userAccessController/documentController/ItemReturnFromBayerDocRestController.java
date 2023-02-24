package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.ItemReturnFromBayerDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemReturnFromBayerDocResponseDto;
import com.example.kassa3.service.abstracts.document.ItemReturnFromBayerDocService;
import com.example.kassa3.util.validation.dtoDataValidation.ItemReturnFromBayerDocValidation;
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
@RequestMapping("/api/user/itemReturnFromBayerDocs")
public class ItemReturnFromBayerDocRestController {

    private final ItemReturnFromBayerDocService itemReturnFromBayerDocService;
    private final ItemReturnFromBayerDocResponseConverter itemReturnFromBayerDocResponseConverter;
    private final ItemReturnFromBayerDocValidation itemReturnFromBayerDocValidation;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createItemReturnFromBayerDoc(@Valid @RequestBody ItemReturnFromBayerDocCreateDto itemReturnFromBayerDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemReturnFromBayerDocResponseConverter.toDTO(itemReturnFromBayerDocValidation.validateAndPersist(itemReturnFromBayerDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<ItemReturnFromBayerDocResponseDto> getAllItemReturnFromBayerDoc() {
        return itemReturnFromBayerDocResponseConverter.toDTOList(itemReturnFromBayerDocService.findAll());
    }

    @PutMapping("/deactivate/{itemReturnFromBayerDocId}")
    public ResponseEntity<? extends Object> deactivateItemReturnFromBayerDoc(@PathVariable Long itemReturnFromBayerDocId, Principal principal) {
        return new ResponseEntity<>(itemReturnFromBayerDocResponseConverter.toDTO(itemReturnFromBayerDocValidation.validateAndDeactivate(itemReturnFromBayerDocId, principal)), HttpStatus.OK);
    }
}
