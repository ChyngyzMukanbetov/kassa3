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
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "country",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<City> cities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Country country = (Country) o;
        return id != null && Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setCities(List<City> cities) {
        if (this.cities == null) {
            this.cities = new ArrayList<>();
        }
        this.cities.clear();
        if (cities != null) {
            this.cities.addAll(cities);
        }
    }
}
