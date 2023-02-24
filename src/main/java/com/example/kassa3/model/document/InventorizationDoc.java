package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InventorizationDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D10";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate inventorizationDate;

    private String comment;

    @Builder.Default
    private boolean itemInventorizationActivate = false;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "inventorizationDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<InventorizationDetails> inventorizationDetailsList;

    @Builder.Default
    private boolean balanceInventorizationActivate = false;

    @Builder.Default
    private BigDecimal cashSumOld = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal nonCashSumOld = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal cashSumNew = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal nonCashSumNew = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal cashSumDifference = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal nonCashSumDifference = BigDecimal.ZERO;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setInventorizationDetailsList(List<InventorizationDetails> inventorizationDetailsList) {
        if (this.inventorizationDetailsList == null) {
            this.inventorizationDetailsList = new ArrayList<>();
        }
        this.inventorizationDetailsList.clear();
        if (inventorizationDetailsList != null) {
            this.inventorizationDetailsList.addAll(inventorizationDetailsList);
            for (InventorizationDetails inventorizationDetails : inventorizationDetailsList) {
                if (inventorizationDetails != null) {
                    inventorizationDetails.setInventorizationDoc(this);
                }
            }
        }
    }
}
