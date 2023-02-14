package com.example.kassa3.model.entity;

import com.example.kassa3.model.document.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Shop implements Serializable {

    private static final long serialVersionUID = 156977875169457L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String shopName;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    @Builder.Default
    private boolean activate = true;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Address address;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<CreditDoc> creditDocList;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<DebitDoc> debitDocList;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<IncomeDoc> incomeDocList;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ItemArrivalDoc> itemArrivalDocList;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ItemSellDoc> itemSellDocList;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ItemWriteOffDoc> itemWriteOffDocList;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<PaymentDoc> paymentDocList;

    public void setItems(List<Item> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
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

    public void setDebitDocList(List<DebitDoc> debitDocList) {
        if (this.debitDocList == null) {
            this.debitDocList = new ArrayList<>();
        }
        this.debitDocList.clear();
        if (debitDocList != null) {
            this.debitDocList.addAll(debitDocList);
        }
    }

    public void setIncomeDocList(List<IncomeDoc> incomeDocList) {
        if (this.incomeDocList == null) {
            this.incomeDocList = new ArrayList<>();
        }
        this.incomeDocList.clear();
        if (incomeDocList != null) {
            this.incomeDocList.addAll(incomeDocList);
        }
    }

    public void setItemArrivalDocList(List<ItemArrivalDoc> itemArrivalDocList) {
        if (this.itemArrivalDocList == null) {
            this.itemArrivalDocList = new ArrayList<>();
        }
        this.itemArrivalDocList.clear();
        if (itemArrivalDocList != null) {
            this.itemArrivalDocList.addAll(itemArrivalDocList);
        }
    }

    public void setItemSellDocList(List<ItemSellDoc> itemSellDocList) {
        if (this.itemSellDocList == null) {
            this.itemSellDocList = new ArrayList<>();
        }
        this.itemSellDocList.clear();
        if (itemSellDocList != null) {
            this.itemSellDocList.addAll(itemSellDocList);
        }
    }

    public void setItemWriteOffDocList(List<ItemWriteOffDoc> itemWriteOffDocList) {
        if (this.itemWriteOffDocList == null) {
            this.itemWriteOffDocList = new ArrayList<>();
        }
        this.itemWriteOffDocList.clear();
        if (itemWriteOffDocList != null) {
            this.itemWriteOffDocList.addAll(itemWriteOffDocList);
        }
    }

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
