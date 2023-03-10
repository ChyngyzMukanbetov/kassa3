package com.example.kassa3.model.entity;

import com.example.kassa3.model.document.*;
import com.example.kassa3.model.enums.Gender;
import lombok.*;
import org.hibernate.Hibernate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
//    public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @ToString.Exclude
    private Long id;

    @Column(nullable = false, unique = true)
    @ToString.Exclude
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @ToString.Exclude
    @Column(nullable = false)
    private String password;

    private LocalDate userRegistrationDate;

    private String confirmationToken;
    private boolean confirm;

    @Builder.Default
    private boolean activate = true;
    private String activationCode;
    private LocalDate ActivateStartDate;
    private LocalDate ActivateExpiryDate;

    private boolean isUsing2FA;
    private String secret;
    private boolean isIdentification;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH},
            optional = false, orphanRemoval = true)
    @ToString.Exclude
    private Phone phone;

    @ToString.Exclude
    private String firstName;
    @ToString.Exclude
    private String lastName;

    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private Gender gender;

    @ToString.Exclude
    private LocalDate birthday;

//    @Enumerated(EnumType.STRING)
//    private Roles role;

    @OneToMany(
            mappedBy = "user",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private Set<Shop> shops;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return new HashSet<>(Arrays.asList(role));
//    }

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<Item> items;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private Set<Category> categories;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private Set<Partner> partners;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<CreditDoc> creditDocList;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<CreditPaymentDoc> creditPaymentDocList;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<DebitDoc> debitDocList;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<DebitIncomeDoc> debitIncomeDocList;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<ItemArrivalDoc> itemArrivalDocList;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<ItemSellDoc> itemSellDocList;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<ItemWriteOffDoc> itemWriteOffDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalCreditDoc> additionalCreditDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalPaymentDoc> additionalPaymentDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalDebitDoc> additionalDebitDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdditionalIncomeDoc> additionalIncomeDocList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InventorizationDoc> inventorizationDocList;

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return activate;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setShops(Set<Shop> shops) {
        if (this.shops == null) {
            this.shops = new HashSet<>();
        }
        this.shops.clear();
        if (shops != null) {
            this.shops.addAll(shops);
        }
    }

    public void setItems(List<Item> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
    }

    public void setCategories(Set<Category> categories) {
        if (this.categories == null) {
            this.categories = new HashSet<>();
        }
        this.categories.clear();
        if (categories != null) {
            this.categories.addAll(categories);
        }
    }

    public void setPartners(Set<Partner> partners) {
        if (this.partners == null) {
            this.partners = new HashSet<>();
        }
        this.partners.clear();
        if (partners != null) {
            this.partners.addAll(partners);
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