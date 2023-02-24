package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalIncomeDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D03";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Min(value = 0)
    @Builder.Default
    private BigDecimal sum = BigDecimal.valueOf(0);

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate incomeDate;

    @Builder.Default
    private boolean nonCash = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private AdditionalDebitDoc additionalDebitDoc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
