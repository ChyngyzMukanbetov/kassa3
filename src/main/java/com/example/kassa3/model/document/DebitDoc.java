package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    private boolean activate = true;
    private boolean isReturned = false;

    private LocalDate documentData = LocalDate.now();

    private LocalDate debitData;

    private LocalDate returnDate;

    private BigDecimal sumOfDebt;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private Partner partner;

    @OneToOne(fetch = FetchType.LAZY)
    private ItemSellDoc itemSellDoc;

    @OneToMany(
            mappedBy = "debitDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<IncomeDoc> incomeDocList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void setIncomeDocList(List<IncomeDoc> incomeDocList) {
        if (this.incomeDocList == null) {
            this.incomeDocList = new ArrayList<>();
        }
        this.incomeDocList.clear();
        if (incomeDocList != null) {
            this.incomeDocList.addAll(incomeDocList);
        }
    }
}
