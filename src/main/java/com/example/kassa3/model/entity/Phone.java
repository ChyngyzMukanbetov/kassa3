package com.example.kassa3.model.entity;

import com.example.kassa3.model.enums.PhoneCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"phone_code", "phone_number"}))
public class Phone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @ToString.Exclude
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_code", nullable = false)
    private PhoneCode phoneCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;
}
