package com.example.kassa3.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private BigDecimal cashSum = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal nonCashSum = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal creditSum = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal debitSum = BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Shop shop;
}
