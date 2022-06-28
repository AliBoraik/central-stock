package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 11:59 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Entity
@Table(name = "bank")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bank {
    @Id
    @GeneratedValue
    private UUID innerId;
    private String name;
    private String address;
    @ManyToOne
    @JoinColumn(name = "country_id", unique = true)
    private Country country;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.PERSIST)
    Collection<Account> accounts;
}
