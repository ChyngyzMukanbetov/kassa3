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
public class DebitDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D07";

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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private ItemSellDoc itemSellDoc;

    @OneToMany(
            mappedBy = "debitDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<DebitIncomeDoc> debitIncomeDocList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setDebitIncomeDocList(List<DebitIncomeDoc> debitIncomeDocList) {
        if (this.debitIncomeDocList == null) {
            this.debitIncomeDocList = new ArrayList<>();
        }
        this.debitIncomeDocList.clear();
        if (debitIncomeDocList != null) {
            this.debitIncomeDocList.addAll(debitIncomeDocList);
            for (DebitIncomeDoc debitIncomeDoc : debitIncomeDocList) {
                debitIncomeDoc.setDebitDoc(this);
            }
        }
    }
}
