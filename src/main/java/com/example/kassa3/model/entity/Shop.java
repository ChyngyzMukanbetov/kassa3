package com.example.kassa3.model.entity;

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
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String shopName;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<Item> items;

    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    @ToString.Exclude
    private List<ArrivalDoc> arrivalDocs;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Address address;

    public void setItems(List<Item> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
    }
}
