package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.PartnerDto;
import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.service.abstracts.PartnerService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PartnerResolver {

    public final PartnerService partnerService;

    @ObjectFactory
    public Partner resolve(PartnerDto dto, @TargetType Class<Item> type) {
        Partner partner;
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            partner = new Partner();
        } else {
            partner = partnerService.findById(dto.getId());
        }
        return partner;
    }
}
