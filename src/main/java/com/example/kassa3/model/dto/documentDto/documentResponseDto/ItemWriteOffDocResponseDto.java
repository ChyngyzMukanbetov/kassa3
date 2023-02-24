package com.example.kassa3.model.dto.documentDto.documentResponseDto;

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
public class ItemWriteOffDocResponseDto {
    private Long id;
    private String docCode;
    private boolean activate;
    private LocalDate deactivateDate;
    private LocalDateTime documentDateTime;
    private LocalDate itemWriteOffDate;
    private String comment;
    private List<ItemWriteOffDetailsResponseDto> itemWriteOffDetailsList;
    private Long shopId;
    private Long userId;
}
