package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Item;
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
public class ItemWriteOffDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private boolean activate = true;

    //цена закупки товара
    @Min(value = 0)
    @Builder.Default
    private BigDecimal basePrice = BigDecimal.valueOf(0);

    @Min(value = 0)
    @Builder.Default
    private BigDecimal count = BigDecimal.valueOf(0);

    @Builder.Default
    private BigDecimal totalSum = BigDecimal.valueOf(0);

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ItemWriteOffDoc itemWriteOffDoc;

    public void updateTotalSum() {
        totalSum = basePrice.multiply(count);
    }

    public void setPrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        updateTotalSum();
    }

    public void setCount(BigDecimal count) {
        this.count = count;
        updateTotalSum();
    }
}
