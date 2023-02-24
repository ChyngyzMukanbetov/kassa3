package com.example.kassa3.web.controller.userAccessController.documentController;

import com.example.kassa3.converter.documentConverter.documentResponseConverter.ItemReturnToProviderDocResponseConverter;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDocCreateDto;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemReturnToProviderDocResponseDto;
import com.example.kassa3.service.abstracts.document.ItemReturnToProviderDocService;
import com.example.kassa3.util.validation.dtoDataValidation.ItemReturnToProviderDocValidation;
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
@RequestMapping("/api/user/itemReturnToProviderDocs")
public class ItemReturnToProviderDocRestController {

    private final ItemReturnToProviderDocService itemReturnToProviderDocService;
    private final ItemReturnToProviderDocResponseConverter itemReturnToProviderDocResponseConverter;
    private final ItemReturnToProviderDocValidation itemReturnToProviderDocValidation;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createItemReturnToProviderDoc(@Valid @RequestBody ItemReturnToProviderDocCreateDto itemReturnToProviderDocCreateDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemReturnToProviderDocResponseConverter.toDTO(itemReturnToProviderDocValidation.validateAndPersist(itemReturnToProviderDocCreateDto, principal)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<ItemReturnToProviderDocResponseDto> getAllItemReturnToProviderDoc() {
        return itemReturnToProviderDocResponseConverter.toDTOList(itemReturnToProviderDocService.findAll());
    }

    @PutMapping("/deactivate/{itemReturnToProviderDocId}")
    public ResponseEntity<? extends Object> deactivateItemReturnToProviderDoc(@PathVariable Long itemReturnToProviderDocId, Principal principal) {
        return new ResponseEntity<>(itemReturnToProviderDocResponseConverter.toDTO(itemReturnToProviderDocValidation.validateAndDeactivate(itemReturnToProviderDocId, principal)), HttpStatus.OK);
    }
}
