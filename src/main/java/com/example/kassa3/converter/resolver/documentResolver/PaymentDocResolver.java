package com.example.kassa3.converter.resolver.documentResolver;

import com.example.kassa3.model.document.PaymentDoc;
import com.example.kassa3.model.dto.documentDto.PaymentDocDto;
import com.example.kassa3.service.abstracts.document.PaymentDocService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentDocResolver {

    private final PaymentDocService paymentDocService;

    @ObjectFactory
    public PaymentDoc resolve(PaymentDocDto dto, @TargetType Class<PaymentDoc> type) {
        PaymentDoc paymentDoc;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            paymentDoc = new PaymentDoc();
        } else {
            paymentDoc = paymentDocService.findById(dto.getId());
        }
        return paymentDoc;
    }
}
