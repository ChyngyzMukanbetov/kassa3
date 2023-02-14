package com.example.kassa3.model.document;

import com.example.kassa3.model.entity.Shop;
import com.example.kassa3.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ItemWriteOffDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Builder.Default
    private boolean activate = true;

    @Builder.Default
    private LocalDate documentData  = LocalDate.now();

    private LocalDate itemWriteOffData;

    private String comment;

    @OneToMany(
            mappedBy = "itemWriteOffDoc",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<ItemWriteOffDetails> itemWriteOffDetailsList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

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
