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
public class ItemArrivalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private boolean activate = true;

    //цена закупки товара (при наличии на складе ранее закупленных товаров;
    // общая цена закупки расчитывается как средняя арифметическая взвешенная, если useBasePriceWAM = true(по умолчанию, true))
    @Min(value = 0)
    private BigDecimal basePrice = BigDecimal.valueOf(0);

    //использовать ли среднюю арифметическую взвешанную для расчета цены закупки
    boolean useBasePriceWAM = true;

    @Min(value = 0)
    private BigDecimal price = BigDecimal.valueOf(0); //цена реализации

    //использовать ли среднюю арифметическую взвешанную для расчета цены на продажу
    boolean usePriceWAM = false;

    @Min(value = 0)
    private BigDecimal count = BigDecimal.valueOf(0);

    @Min(value = 0)
    private BigDecimal sum = BigDecimal.valueOf(0); //basePrice * count

    private boolean isNonCash = false;
    private boolean isOnCredit;

    @Min(value = 0)
    private BigDecimal sumOfCredit = BigDecimal.valueOf(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private ItemArrivalDoc itemArrivalDoc;

    public void updateSum() {
        sum = price.multiply(count);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        updateSum();
    }

    public void setCount(BigDecimal count) {
        this.count = count;
        updateSum();
    }
}
