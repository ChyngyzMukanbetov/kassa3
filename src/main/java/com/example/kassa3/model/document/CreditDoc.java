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
public class CreditDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private boolean activate = true;

    @Builder.Default
    private boolean returned = false;

    @Builder.Default
    private LocalDate documentData = LocalDate.now();

    private LocalDate creditData;

    private LocalDate returnDate;

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
    private List<PaymentDoc> paymentDocList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setPaymentDocList(List<PaymentDoc> paymentDocList) {
        if (this.paymentDocList == null) {
            this.paymentDocList = new ArrayList<>();
        }
        this.paymentDocList.clear();
        if (paymentDocList != null) {
            this.paymentDocList.addAll(paymentDocList);
        }
    }
}
