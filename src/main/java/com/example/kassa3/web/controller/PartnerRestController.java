package com.example.kassa3.web.controller;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.dto.PartnerDto;
import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.service.abstracts.PartnerService;
import com.example.kassa3.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/partners")
public class PartnerRestController {

    private final PartnerConverter partnerConverter;
    private final PartnerService partnerService;
    private final UserService userService;

    @GetMapping("/getAll")
    public List<PartnerDto> getAllPartner() {
        return partnerConverter.toDTOList(partnerService.findAll());
    }

    @GetMapping("/getAllActivate")
    public List<PartnerDto> getAllActivatePartner() {
        return partnerConverter.toDTOList(partnerService.findAllActivate());
    }

//    @GetMapping("/getAllDeleted")
//    public List<ItemDto> getAllDeletedItems() {
//        return itemConverter.toDTOList(itemService.findAllDeletedByUser());
//    }

    @GetMapping("/getAllDeactivate")
    public List<PartnerDto> getAllDeactivatePartner() {
        return partnerConverter.toDTOList(partnerService.findAllDeactivate());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPartner(@Valid @RequestBody PartnerDto partnerDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (partnerDto.getId() != null) {
            return new ResponseEntity<>("id должно быть пустым", HttpStatus.BAD_REQUEST);
        }

        // check if user exists
        // (добавить:
        // 1) проверку юзера с принципалом
        // 2) проверку userConfirmed, если нет отправить токен на почту
        // 3) проверить activated, если нет то отправить сообщение о необходимости оплаты и ссылку на страницу оплаты

//        if (itemCreateDto.getUserId() == null) {
//            itemCreateDto.setUserId(userService.findByUsername(principal.getName()).getId());
//        } else if (!userService.existsById(itemCreateDto.getUserId())) {
//            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
//        }

        if (!userService.existsById(partnerDto.getUserId())) {
            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
        }

        // save user to the database
        partnerService.persist(partnerConverter.toModel(partnerDto));

        return new ResponseEntity<>("Partner успешно сохранен", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePartner(@Valid @RequestBody PartnerDto partnerDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (partnerDto.getId() == null) {
            return new ResponseEntity<>("id не должно быть пустым", HttpStatus.BAD_REQUEST);
        }

        if (!partnerService.existsById(partnerDto.getId())) {
            return new ResponseEntity<>("id не верный", HttpStatus.BAD_REQUEST);
        }

        // check if user exists
        // (добавить:
        // 1) проверку юзера с принципалом
        // 2) проверку userConfirmed, если нет отправить токен на почту
        // 3) проверить activated, если нет то отправить сообщение о необходимости оплаты и ссылку на страницу оплаты

//        if (itemCreateDto.getUserId() == null) {
//            itemCreateDto.setUserId(userService.findByUsername(principal.getName()).getId());
//        } else if (!userService.existsById(itemCreateDto.getUserId())) {
//            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
//        }

        if (!userService.existsById(partnerDto.getUserId())) {
            return new ResponseEntity<>("User не существует", HttpStatus.CONFLICT);
        }

        // save user to the database
        partnerService.update(partnerConverter.toModel(partnerDto));

        return new ResponseEntity<>("Partner успешно обновлен", HttpStatus.OK);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivatePartner(@PathVariable Long id) {
        if (!partnerService.existsById(id)) {
            return new ResponseEntity<>("Неверный id категории", HttpStatus.BAD_REQUEST);
        }
        Partner partner = partnerService.findById(id);
        partner.setActivate(false);
        partnerService.update(partner);
        return new ResponseEntity<>("Partner успешно помечен неактивным", HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activatePartner(@PathVariable Long id) {
        if (!partnerService.existsById(id)) {
            return new ResponseEntity<>("Неверный id категории", HttpStatus.BAD_REQUEST);
        }
        Partner partner = partnerService.findById(id);
        partner.setActivate(true);
        partnerService.update(partner);
        return new ResponseEntity<>("Partner успешно восстановлен", HttpStatus.OK);
    }
}
