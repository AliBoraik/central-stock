package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bank_account")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Country country;

    private double balance;

    @ManyToOne
    private Bank bank;

}
