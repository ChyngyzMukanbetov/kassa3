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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH},
            optional = false, orphanRemoval = true)
    @ToString.Exclude
    private Balance balance;

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
    private List<DebitIncomeDoc> debitIncomeDocList;

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
    private List<CreditPaymentDoc> creditPaymentDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalCreditDoc> additionalCreditDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalPaymentDoc> additionalPaymentDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalDebitDoc> additionalDebitDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalIncomeDoc> additionalIncomeDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InventorizationDoc> inventorizationDocList;

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

    public void setDebitIncomeDocList(List<DebitIncomeDoc> debitIncomeDocList) {
        if (this.debitIncomeDocList == null) {
            this.debitIncomeDocList = new ArrayList<>();
        }
        this.debitIncomeDocList.clear();
        if (debitIncomeDocList != null) {
            this.debitIncomeDocList.addAll(debitIncomeDocList);
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

    public void setCreditPaymentDocList(List<CreditPaymentDoc> creditPaymentDocList) {
        if (this.creditPaymentDocList == null) {
            this.creditPaymentDocList = new ArrayList<>();
        }
        this.creditPaymentDocList.clear();
        if (creditPaymentDocList != null) {
            this.creditPaymentDocList.addAll(creditPaymentDocList);
        }
    }

    public void setAdditionalCreditDocList(List<AdditionalCreditDoc> additionalCreditDocList) {
        if (this.additionalCreditDocList == null) {
            this.additionalCreditDocList = new ArrayList<>();
        }
        this.additionalCreditDocList.clear();
        if (additionalCreditDocList != null) {
            this.additionalCreditDocList.addAll(additionalCreditDocList);
        }
    }

    public void setAdditionalPaymentDocList(List<AdditionalPaymentDoc> additionalPaymentDocList) {
        if (this.additionalPaymentDocList == null) {
            this.additionalPaymentDocList = new ArrayList<>();
        }
        this.additionalPaymentDocList.clear();
        if (additionalPaymentDocList != null) {
            this.additionalPaymentDocList.addAll(additionalPaymentDocList);
        }
    }

    public void setAdditionalDebitDocList(List<AdditionalDebitDoc> additionalDebitDocList) {
        if (this.additionalDebitDocList == null) {
            this.additionalDebitDocList = new ArrayList<>();
        }
        this.additionalDebitDocList.clear();
        if (additionalDebitDocList != null) {
            this.additionalDebitDocList.addAll(additionalDebitDocList);
        }
    }

    public void setAdditionalIncomeDocList(List<AdditionalIncomeDoc> additionalIncomeDocList) {
        if (this.additionalIncomeDocList == null) {
            this.additionalIncomeDocList = new ArrayList<>();
        }
        this.additionalIncomeDocList.clear();
        if (additionalIncomeDocList != null) {
            this.additionalIncomeDocList.addAll(additionalIncomeDocList);
        }
    }
    public void setInventorizationDocList(List<InventorizationDoc> inventorizationDocList) {
        if (this.inventorizationDocList == null) {
            this.inventorizationDocList = new ArrayList<>();
        }
        this.inventorizationDocList.clear();
        if (inventorizationDocList != null) {
            this.inventorizationDocList.addAll(inventorizationDocList);
        }
    }
}