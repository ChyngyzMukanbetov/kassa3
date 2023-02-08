package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Item;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

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

    private boolean activate = true;

    //цена закупки товара
    @Min(value = 0)
    private BigDecimal basePrice = BigDecimal.valueOf(0);

    @Min(value = 0)
    private BigDecimal price = BigDecimal.valueOf(0);

    @Min(value = 0)
    private BigDecimal count = BigDecimal.valueOf(0);

    private BigDecimal sum = BigDecimal.valueOf(0);

    @Min(value = 0)
    private BigDecimal discountSum = BigDecimal.valueOf(0);

    private BigDecimal totalSum = BigDecimal.valueOf(0);

    private boolean isNonCash = false;
    private boolean isOnDebt = false;

    @Min(value = 0)
    private BigDecimal sumOfDebt = BigDecimal.valueOf(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private ItemSellDoc itemSellDoc;

    public void updateSum() {
        sum = price.multiply(count);
        updateTotalSum();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        updateSum();
    }

    public void setCount(BigDecimal count) {
        this.count = count;
        updateSum();
    }

    public void setDiscountSum(BigDecimal discountSum) {
        if (discountSum.compareTo(sum) > 0) {
            throw new IllegalArgumentException("Discount sum cannot be greater than sum");
        }
        this.discountSum = discountSum;
        updateTotalSum();
    }

    private void updateTotalSum() {
        totalSum = sum.subtract(discountSum);
    }
}
