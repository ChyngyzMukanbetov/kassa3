package com.example.kassa3.model.dto.documentDto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemWriteOffDocDto {
    private Long id;

    private boolean activate;

    private LocalDate documentData;

    private LocalDate itemWriteOffData;

    private String comment;

    private List<ItemWriteOffDetailsDto> itemWriteOffDetailsList;

    private Long shopId;

    private Long userId;
}
