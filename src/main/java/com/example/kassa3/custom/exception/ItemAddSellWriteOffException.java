package com.example.kassa3.custom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddSellWriteOffException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
}
