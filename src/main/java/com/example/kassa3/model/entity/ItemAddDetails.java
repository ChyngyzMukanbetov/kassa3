package com.example.kassa3.model.entity;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

}
