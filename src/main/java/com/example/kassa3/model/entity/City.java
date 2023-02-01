package com.example.kassa3.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city_index", nullable = false, unique = true)
    private String cityIndex;

    @OneToMany(
            mappedBy = "city",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = false, fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Address> addresses;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Country country;

    public City(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;
        return id != null && Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setAddresses(List<Address> addresses) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.clear();
        if (addresses != null) {
            this.addresses.addAll(addresses);
        }
    }
}
