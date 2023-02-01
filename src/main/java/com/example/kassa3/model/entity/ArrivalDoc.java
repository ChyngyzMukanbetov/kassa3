package com.example.kassa3.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArrivalDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ToString.Exclude
    private Calendar documentData;
    @ToString.Exclude
    private Calendar itemArrivalData;

    @OneToMany(
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    @JoinColumn(name = "item_add_details_id")
    @ToString.Exclude
    private List<ItemAddDetails> itemAddDetailsList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner")
    @ToString.Exclude
    private Partner partner;

    @ToString.Exclude
    private boolean isOnDebt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    public void setItemAddDetailsList(List<ItemAddDetails> itemAddDetailsList) {
        if (this.itemAddDetailsList == null) {
            this.itemAddDetailsList = new ArrayList<>();
        }
        this.itemAddDetailsList.clear();
        if (itemAddDetailsList != null) {
            this.itemAddDetailsList.addAll(itemAddDetailsList);
        }
    }
}
