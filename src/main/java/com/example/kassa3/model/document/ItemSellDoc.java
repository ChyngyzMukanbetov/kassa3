package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ItemSellDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private boolean activate = true;

    private LocalDate documentData  = LocalDate.now();

    private LocalDate itemSellData;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private User user;

    public void setItemSellDetailsList(List<ItemSellDetails> itemSellDetailsList) {
        if (this.itemSellDetailsList == null) {
            this.itemSellDetailsList = new ArrayList<>();
        }
        this.itemSellDetailsList.clear();
        if (itemSellDetailsList != null) {
            this.itemSellDetailsList.addAll(itemSellDetailsList);
        }
    }
}
