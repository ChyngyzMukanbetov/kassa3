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
public class CreditDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D06";

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

    private LocalDate creditDate;

    private LocalDate returnPlanDate;
    private LocalDate returnFactDate;

    private BigDecimal sumOfCredit;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Partner partner;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private ItemArrivalDoc itemArrivalDoc;

    @OneToMany(
            mappedBy = "creditDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<CreditPaymentDoc> creditPaymentDocList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setCreditPaymentDocList(List<CreditPaymentDoc> creditPaymentDocList) {
        if (this.creditPaymentDocList == null) {
            this.creditPaymentDocList = new ArrayList<>();
        }
        this.creditPaymentDocList.clear();
        if (creditPaymentDocList != null) {
            this.creditPaymentDocList.addAll(creditPaymentDocList);
            for (CreditPaymentDoc creditPaymentDoc : creditPaymentDocList) {
                creditPaymentDoc.setCreditDoc(this);
            }
        }
    }
}
