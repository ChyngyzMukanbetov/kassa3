package com.example.kassa3.model.dto.documentDto.documentResponseDto;

import com.example.kassa3.model.dto.PartnerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemArrivalDocResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private LocalDateTime documentDateTime;
    private LocalDate itemArrivalDate;
    private String comment;
    private List<ItemArrivalDetailsResponseDto> itemArrivalDetailsList;
    private CreditDocResponseDto creditDoc;
    private PartnerDto partner;
    private Long shopId;
    private Long userId;
}
