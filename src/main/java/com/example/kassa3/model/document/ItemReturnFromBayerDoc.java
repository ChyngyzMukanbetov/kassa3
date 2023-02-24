package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ItemReturnFromBayerDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D14";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate itemReturnFromBayerDate;

    private String comment;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "itemReturnFromBayerDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<ItemReturnFromBayerDetails> itemReturnFromBayerDetailsList;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setItemReturnFromBayerDetailsList(List<ItemReturnFromBayerDetails> itemReturnFromBayerDetailsList) {
        if (this.itemReturnFromBayerDetailsList == null) {
            this.itemReturnFromBayerDetailsList = new ArrayList<>();
        }
        this.itemReturnFromBayerDetailsList.clear();
        if (itemReturnFromBayerDetailsList != null) {
            this.itemReturnFromBayerDetailsList.addAll(itemReturnFromBayerDetailsList);
            for (ItemReturnFromBayerDetails itemReturnFromBayerDetails : itemReturnFromBayerDetailsList) {
                itemReturnFromBayerDetails.setItemReturnFromBayerDoc(this);
            }
        }
    }
}
