package com.example.kassa3.model.entity;

import com.example.kassa3.model.document.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @ToString.Exclude
    private Long id;

    private boolean isProvider;
    private boolean isBuyer;
    private boolean activate = true;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @ToString.Exclude
    private String phone;

    @ToString.Exclude
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "partner",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemArrivalDoc> itemArrivalDocs;

    @ToString.Exclude
    @OneToMany(mappedBy = "partner",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemSellDoc> itemSellDocs;

    @ToString.Exclude
    @OneToMany(mappedBy = "partner",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DebitDoc> debitDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "partner",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CreditDoc> creditDocList;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "partner",
//            cascade = {CascadeType.MERGE,
//                    CascadeType.PERSIST,
//                    CascadeType.REFRESH,
//                    CascadeType.DETACH},
//            orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<IncomeDoc> incomeDocList;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "partner",
//            cascade = {CascadeType.MERGE,
//                    CascadeType.PERSIST,
//                    CascadeType.REFRESH,
//                    CascadeType.DETACH},
//            orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<PaymentDoc> paymentDocList;

    public void setItemArrivalDocs(List<ItemArrivalDoc> itemArrivalDocs) {
        if (this.itemArrivalDocs == null) {
            this.itemArrivalDocs = new ArrayList<>();
        }
        this.itemArrivalDocs.clear();
        if (itemArrivalDocs != null) {
            this.itemArrivalDocs.addAll(itemArrivalDocs);
        }
    }

    public void setItemSellDocs(List<ItemSellDoc> itemSellDocs) {
        if (this.itemSellDocs == null) {
            this.itemSellDocs = new ArrayList<>();
        }
        this.itemSellDocs.clear();
        if (itemSellDocs != null) {
            this.itemSellDocs.addAll(itemSellDocs);
        }
    }

    public void setDebitDocList(List<DebitDoc> debitDocList) {
        if (this.debitDocList == null) {
            this.debitDocList = new ArrayList<>();
        }
        this.debitDocList.clear();
        if (debitDocList != null) {
            this.debitDocList.addAll(debitDocList);
        }
    }

    public void setCreditDocList(List<CreditDoc> creditDocList) {
        if (this.creditDocList == null) {
            this.creditDocList = new ArrayList<>();
        }
        this.creditDocList.clear();
        if (creditDocList != null) {
            this.creditDocList.addAll(creditDocList);
        }
    }

//    public void setIncomeDocList(List<IncomeDoc> incomeDocList) {
//        if (this.incomeDocList == null) {
//            this.incomeDocList = new ArrayList<>();
//        }
//        this.incomeDocList.clear();
//        if (incomeDocList != null) {
//            this.incomeDocList.addAll(incomeDocList);
//        }
//    }

//    public void setPaymentDocList(List<PaymentDoc> paymentDocList) {
//        if (this.paymentDocList == null) {
//            this.paymentDocList = new ArrayList<>();
//        }
//        this.paymentDocList.clear();
//        if (paymentDocList != null) {
//            this.paymentDocList.addAll(paymentDocList);
//        }
//    }
}
