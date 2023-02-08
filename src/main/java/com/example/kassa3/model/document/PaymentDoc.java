package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Partner;
import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.model.enums.CashOrNot;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private boolean activate = true;

    @Min(value = 0)
    private BigDecimal sum = BigDecimal.valueOf(0);

    private LocalDate documentData  = LocalDate.now();

    private LocalDate paymentData;

    private boolean isNonCash = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private CreditDoc creditDoc;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
