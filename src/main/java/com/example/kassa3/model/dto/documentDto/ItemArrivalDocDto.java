package com.example.kassa3.model.dto.documentDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ItemArrivalDocDto {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate documentData; //игнорим при toModel

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Past(message = "Дата оприходования не может быть в будущем")
    private LocalDate itemArrivalData;

    @Size(max=250, message = "comment should be max 250 letters")
    private String comment;

    @Valid
    @NotNull
    private List<ItemArrivalDetailsDto> itemArrivalDetailsList;

    @Valid
    private CreditDocDto creditDoc;

    @Min(value = 0, message = "partnerId can not be negative")
    private Long partnerId;
    private String partnerName;

    @NotNull(message = "shopId id required")
    @Min(value = 1)
    private Long shopId;

    @NotNull(message = "userId id required")
    @Min(value = 1)
    private Long userId;
}



