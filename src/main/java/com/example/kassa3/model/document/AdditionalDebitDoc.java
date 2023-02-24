package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Partner;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalDebitDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D02";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Builder.Default
    private boolean returned = false;

    @Builder.Default
    private boolean WrittenOff = false;
    private LocalDate WriteOffDate;
    private String WriteOffReason;

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate debitDate;

    private LocalDate returnPlanDate;
    private LocalDate returnFactDate;

    private BigDecimal sumOfDebit;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Partner partner;

    @OneToMany(
            mappedBy = "additionalDebitDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<AdditionalIncomeDoc> additionalIncomeDocList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setDebitIncomeDocList(List<AdditionalIncomeDoc> additionalIncomeDocList) {
        if (this.additionalIncomeDocList == null) {
            this.additionalIncomeDocList = new ArrayList<>();
        }
        this.additionalIncomeDocList.clear();
        if (additionalIncomeDocList != null) {
            this.additionalIncomeDocList.addAll(additionalIncomeDocList);
            for (AdditionalIncomeDoc additionalIncomeDoc : additionalIncomeDocList) {
                additionalIncomeDoc.setAdditionalDebitDoc(this);
            }
        }
    }
}
