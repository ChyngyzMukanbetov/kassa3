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
public class ItemSellDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D18";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Builder.Default
    private LocalDateTime documentDateTime = LocalDateTime.now();

    private LocalDate itemSellDate;

    private String comment;

    @OneToMany(
            mappedBy = "itemSellDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<ItemSellDetails> itemSellDetailsList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @OneToOne(
            mappedBy = "itemSellDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private DebitDoc debitDoc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setItemSellDetailsList(List<ItemSellDetails> itemSellDetailsList) {
        if (this.itemSellDetailsList == null) {
            this.itemSellDetailsList = new ArrayList<>();
        }
        this.itemSellDetailsList.clear();
        if (itemSellDetailsList != null) {
            this.itemSellDetailsList.addAll(itemSellDetailsList);
            for (ItemSellDetails itemSellDetails : itemSellDetailsList) {
                itemSellDetails.setItemSellDoc(this);
            }
        }
    }

    public void setDebitDoc(DebitDoc debitDoc) {
        if (debitDoc != null) {
            debitDoc.setItemSellDoc(this);
        }
        this.debitDoc = debitDoc;
    }
}
