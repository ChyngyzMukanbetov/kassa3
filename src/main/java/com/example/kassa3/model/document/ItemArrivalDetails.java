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
public class ItemArrivalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D11";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    //цена закупки товара (при наличии на складе ранее закупленных товаров;
    // общая цена закупки расчитывается как средняя арифметическая взвешенная, если useBasePriceWAM = true(по умолчанию, true))
    @Min(value = 0)
    @Builder.Default
    private BigDecimal basePrice = BigDecimal.valueOf(0);

    //использовать ли среднюю арифметическую взвешанную для расчета цены закупки
    @Builder.Default
    private boolean useBasePriceWAM = true;

    @Min(value = 0)
    @Builder.Default
    private BigDecimal price = BigDecimal.valueOf(0); //цена реализации

    //использовать ли среднюю арифметическую взвешанную для расчета цены на продажу
    @Builder.Default
    private boolean usePriceWAM = false;

    @Min(value = 0)
    @Builder.Default
    private BigDecimal count = BigDecimal.valueOf(0);

    @Min(value = 0)
    @Builder.Default
    private BigDecimal sum = BigDecimal.valueOf(0); //basePrice * count

    @Builder.Default
    private boolean nonCash = false;

    @Builder.Default
    private boolean onCredit = false;

    @Min(value = 0)
    @Builder.Default
    private BigDecimal sumOfCredit = BigDecimal.valueOf(0);

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ItemArrivalDoc itemArrivalDoc;
}
