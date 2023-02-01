package com.example.kassa3.model.entity;

import com.example.kassa3.model.enums.Gender;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Check;
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
@ToString
public class User {
//    public class User implements UserDetails {

    public User() {
        this.secret = UUID.randomUUID().toString();
        this.gender = Gender.UNKNOWN;
        this.userRegistrationDate = LocalDate.now();Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        this.age = 0;
        Shop shop = new Shop();
        shop.setShopName("Основной");
        Set<Shop> shops = new HashSet<>();
        shops.add(shop);
        this.setShops(shops);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @ToString.Exclude
    private Long id;

    @Column(name = "email", unique = true)
    @ToString.Exclude
    private String email;

    @Column(name = "username", unique = true)
    @ToString.Exclude
    private String username;

    @ToString.Exclude
    private String password;

    private LocalDate userRegistrationDate;

    private String confirmationToken;
    private boolean confirm;

    private boolean activate;
    private String activationCode;
    private LocalDate ActivateStartDate;
    private LocalDate ActivateExpiryDate;

    private boolean isUsing2FA;
    private String secret;
    private boolean isIdentification;

    @OneToOne(mappedBy = "user",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Phone phone;

    @ToString.Exclude
    private String firstName;
    @ToString.Exclude
    private String lastName;

    @ToString.Exclude
    @Check(constraints = "age >= 0")
    private Integer age;

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
    private List<ArrivalDoc> arrivalDocs;

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
}
