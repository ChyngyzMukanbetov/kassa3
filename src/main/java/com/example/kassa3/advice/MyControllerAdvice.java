package com.example.kassa3.advice;

import com.example.kassa3.custom.exception.ItemAddSellWriteOffException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(ItemAddSellWriteOffException.class)
    public ResponseEntity<String> handleItemAddSellWriteOffException(ItemAddSellWriteOffException itemAddSellWriteOffException) {
        return new ResponseEntity<>(itemAddSellWriteOffException.getErrorCode() + " " + itemAddSellWriteOffException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleConstraintViolationException(InvalidFormatException invalidFormatException) {
        return new ResponseEntity<>(invalidFormatException.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
