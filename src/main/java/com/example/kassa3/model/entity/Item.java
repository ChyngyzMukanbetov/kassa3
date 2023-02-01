package com.example.kassa3.model.entity;

import com.example.kassa3.model.enums.Color;
import com.example.kassa3.model.enums.Measure;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 156977875169457L;

    public Item() {
        this.count = 0;
        this.basePrice = BigDecimal.valueOf(0);
        this.price = BigDecimal.valueOf(0);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @ToString.Exclude
    private String description;

    @Column(name = "base_price")
    @Check(constraints = "base_price >= 0")
    @ToString.Exclude
    private BigDecimal basePrice;

    @Column(name = "price")
    @Check(constraints = "price >= 0")
    @ToString.Exclude
    private BigDecimal price;

    @Column(name = "count")
    @Check(constraints = "count >= 0")
    @ToString.Exclude
    private Integer count;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "made_in_country_id")
    @ToString.Exclude
    private Country madeInCountry;

    @ToString.Exclude
    @Column(name = "bar_code_number")
    @Check(constraints = "bar_code_number >= 0")
    private Integer barCodeNumber;

    @ToString.Exclude
    private String barCodeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Shop shop;

    @OneToMany(
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    @JoinColumn(name = "item_id")
    @ToString.Exclude
    private List<Image> images;

    public void setImages(List<Image> images) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.clear();
        if (images != null) {
            this.images.addAll(images);
        }
    }
}
