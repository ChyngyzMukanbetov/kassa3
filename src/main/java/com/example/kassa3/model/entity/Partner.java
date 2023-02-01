package com.example.kassa3.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @ToString.Exclude
    private Long id;

    boolean isProvider;
    boolean isBuyer;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "phone", unique = true, nullable = false)
    @ToString.Exclude
    private String phone;

    @ToString.Exclude
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;
}
