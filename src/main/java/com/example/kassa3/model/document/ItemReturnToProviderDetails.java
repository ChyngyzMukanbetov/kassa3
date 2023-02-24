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
public class ItemReturnToProviderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private final String docCode = "D15";

    @Builder.Default
    private boolean activate = true;
    private LocalDate deactivateDate;

    @Min(value = 0)
    @Builder.Default
    private BigDecimal price = BigDecimal.valueOf(0);

    @Min(value = 0)
    @Builder.Default
    private BigDecimal count = BigDecimal.valueOf(0);

    @Builder.Default
    @Min(value = 0)
    private BigDecimal sum = BigDecimal.valueOf(0);

    @Builder.Default
    private boolean nonCash = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ItemReturnToProviderDoc itemReturnToProviderDoc;
}
