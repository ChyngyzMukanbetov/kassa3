package com.example.kassa3.model.entity;

import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.enums.Color;
import com.example.kassa3.model.enums.Measure;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"item_name", "user_id"}))
public class Item implements Serializable {

    private static final long serialVersionUID = 20230212L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @ToString.Exclude
    private String description;

    @Column(name = "base_price")
    @Min(value = 0)
    @ToString.Exclude
    @Builder.Default
    private BigDecimal basePrice = BigDecimal.ZERO;

    @Column(name = "price")
    @Min(value = 0)
    @ToString.Exclude
    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "count")
    @Min(value = 0)
    @ToString.Exclude
    @Builder.Default
    private BigDecimal count = BigDecimal.ZERO;

    @Builder.Default
    private boolean activate = true;
    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Category category;

    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private Color color;

    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private Measure measure;

    @ToString.Exclude
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Country madeInCountry;

    @ToString.Exclude
    @Column(name = "bar_code_number")
    @Min(value = 0)
    private Integer barCodeNumber;

    @ToString.Exclude
    private String barCodeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Shop shop;

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    @ToString.Exclude
    private Image image;

    @OneToMany(mappedBy = "item",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = false, fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ItemArrivalDetails> itemArrivalDetailsList;

    @OneToMany(mappedBy = "item",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = false, fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ItemSellDetails> itemSellDetailsList;

    @OneToMany(mappedBy = "item",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = false, fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ItemWriteOffDetails> itemWriteOffDetailsList;

    public void setItemArrivalDetailsList(List<ItemArrivalDetails> itemArrivalDetailsList) {
        if (this.itemArrivalDetailsList == null) {
            this.itemArrivalDetailsList = new ArrayList<>();
        }
        this.itemArrivalDetailsList.clear();
        if (itemArrivalDetailsList != null) {
            this.itemArrivalDetailsList.addAll(itemArrivalDetailsList);
        }
    }

    public void setItemSellDetailsList(List<ItemSellDetails> itemSellDetailsList) {
        if (this.itemSellDetailsList == null) {
            this.itemSellDetailsList = new ArrayList<>();
        }
        this.itemSellDetailsList.clear();
        if (itemSellDetailsList != null) {
            this.itemSellDetailsList.addAll(itemSellDetailsList);
        }
    }

    public void setItemWriteOffDetailsList(List<ItemWriteOffDetails> itemWriteOffDetailsList) {
        if (this.itemWriteOffDetailsList == null) {
            this.itemWriteOffDetailsList = new ArrayList<>();
        }
        this.itemWriteOffDetailsList.clear();
        if (itemWriteOffDetailsList != null) {
            this.itemWriteOffDetailsList.addAll(itemWriteOffDetailsList);
        }
    }
}
