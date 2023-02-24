package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Item;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventorizationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D09";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventorizationDoc inventorizationDoc;

    @Column(name = "count_old")
    @Min(value = 0)
    @ToString.Exclude
    @Builder.Default
    private BigDecimal countOld = BigDecimal.ZERO;

    @Column(name = "count_new")
    @Min(value = 0)
    @ToString.Exclude
    @Builder.Default
    private BigDecimal countNew = BigDecimal.ZERO;

    @Column(name = "count_difference")
    @ToString.Exclude
    @Builder.Default
    private BigDecimal countDifference = BigDecimal.ZERO;
}
