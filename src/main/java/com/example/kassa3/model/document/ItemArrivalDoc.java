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
public class ItemArrivalDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D12";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate itemArrivalDate;

    private String comment;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "itemArrivalDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<ItemArrivalDetails> itemArrivalDetailsList;

    @ToString.Exclude
    @OneToOne(
            mappedBy = "itemArrivalDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private CreditDoc creditDoc;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setItemArrivalDetailsList(List<ItemArrivalDetails> itemArrivalDetailsList) {
        if (this.itemArrivalDetailsList == null) {
            this.itemArrivalDetailsList = new ArrayList<>();
        }
        this.itemArrivalDetailsList.clear();
        if (itemArrivalDetailsList != null) {
            this.itemArrivalDetailsList.addAll(itemArrivalDetailsList);
            for (ItemArrivalDetails itemArrivalDetails : itemArrivalDetailsList) {
                itemArrivalDetails.setItemArrivalDoc(this);
            }
        }
    }

    public void setCreditDoc(CreditDoc creditDoc) {
        if (creditDoc != null) {
            creditDoc.setItemArrivalDoc(this);
        }
        this.creditDoc = creditDoc;
    }
}