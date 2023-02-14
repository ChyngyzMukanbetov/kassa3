package com.example.kassa3.web.controller;

import com.example.kassa3.converter.documentConverter.ItemArrivalDocConverter;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDocDto;
import com.example.kassa3.service.abstracts.document.ItemArrivalDocService;
import com.example.kassa3.util.validation.dtoDataValidation.ItemArrivalDocDtoValidation;
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
@RequestMapping("/api/itemArrivalDocs")
public class ItemArrivalDocRestController {

    private final ItemArrivalDocService itemArrivalDocService;
    private final ItemArrivalDocConverter itemArrivalDocConverter;
    private final ItemArrivalDocDtoValidation itemArrivalDocDtoValidation;

    @PostMapping("/create")
    public ResponseEntity<String> createItemArrivalDoc(@Valid @RequestBody ItemArrivalDocDto itemArrivalDocDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        itemArrivalDocService.persist(itemArrivalDocConverter.toModel(itemArrivalDocDtoValidation.validate(itemArrivalDocDto, principal)));
        return new ResponseEntity<>("Товары оприходованы успешно", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<ItemArrivalDocDto> getAllItemArrivalDoc() {
        return itemArrivalDocConverter.toDTOList(itemArrivalDocService.findAll());
    }


}
