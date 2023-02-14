package com.example.kassa3.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private boolean activate = true;

    @Column(name = "street", nullable = false, unique = true)
    private String street;

    @Column(name = "house", nullable = false, unique = true)
    private String house;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private City city;

    @OneToMany(
            mappedBy = "address",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Shop> shops;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setShops(List<Shop> shops) {
        if (this.shops == null) {
            this.shops = new ArrayList<>();
        }
        this.shops.clear();
        if (shops != null) {
            this.shops.addAll(shops);
        }
    }
}
