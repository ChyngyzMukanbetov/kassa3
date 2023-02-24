package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Item;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemSellDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D17";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    //цена закупки товара
    @Min(value = 0)
    @Builder.Default
    private BigDecimal basePrice = BigDecimal.valueOf(0);

    @Min(value = 0)
    @Builder.Default
    private BigDecimal price = BigDecimal.valueOf(0);

    @Min(value = 0)
    @Builder.Default
    private BigDecimal count = BigDecimal.valueOf(0);

    @Builder.Default
    @Min(value = 0)
    private BigDecimal sum = BigDecimal.valueOf(0);

    @Min(value = 0)
    @Builder.Default
    private BigDecimal discountSum = BigDecimal.valueOf(0);

    @Builder.Default
    @Min(value = 0)
    private BigDecimal totalSum = BigDecimal.valueOf(0);

    @Builder.Default
    private boolean nonCash = false;

    @Builder.Default
    private boolean onDebit = false;

    @Min(value = 0)
    @Builder.Default
    private BigDecimal sumOfDebit = BigDecimal.valueOf(0);

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ItemSellDoc itemSellDoc;

//    public void updateSum() {
//        sum = price.multiply(count);
//        updateTotalSum();
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//        updateSum();
//    }
//
//    public void setCount(BigDecimal count) {
//        this.count = count;
//        updateSum();
//    }
//
//    public void setDiscountSum(BigDecimal discountSum) {
//        if (discountSum.compareTo(sum) > 0) {
//            throw new IllegalArgumentException("Discount sum cannot be greater than sum");
//        }
//        this.discountSum = discountSum;
//        updateTotalSum();
//    }
//
//    private void updateTotalSum() {
//        totalSum = sum.subtract(discountSum);
//    }
}
