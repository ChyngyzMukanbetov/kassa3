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
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ItemReturnToProviderDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D16";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate itemReturnToProviderDate;

    private String comment;

    @OneToMany(
            mappedBy = "itemReturnToProviderDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<ItemReturnToProviderDetails> itemReturnToProviderDetailsList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setItemReturnToProviderDetailsList(List<ItemReturnToProviderDetails> itemReturnToProviderDetailsList) {
        if (this.itemReturnToProviderDetailsList == null) {
            this.itemReturnToProviderDetailsList = new ArrayList<>();
        }
        this.itemReturnToProviderDetailsList.clear();
        if (itemReturnToProviderDetailsList != null) {
            this.itemReturnToProviderDetailsList.addAll(itemReturnToProviderDetailsList);
            for (ItemReturnToProviderDetails itemReturnToProviderDetails : itemReturnToProviderDetailsList) {
                itemReturnToProviderDetails.setItemReturnToProviderDoc(this);
            }
        }
    }
}
