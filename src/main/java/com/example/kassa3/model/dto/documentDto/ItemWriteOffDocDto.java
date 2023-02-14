package com.example.kassa3.model.dto.documentDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
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

    @NotNull(message = "activate field is required and must be either true or false")
    private Boolean activate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate documentData;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Past(message = "Дата списания не может быть в будущем")
    private LocalDate itemWriteOffData;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @Valid
    private List<ItemWriteOffDetailsDto> itemWriteOffDetailsList;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}
