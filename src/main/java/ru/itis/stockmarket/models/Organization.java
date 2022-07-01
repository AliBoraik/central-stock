package ru.itis.stockmarket.models;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Organization {
    @Id
    @GeneratedValue
    private UUID innerId;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    /* deleting an org is therefore harmful as all products they sell are discarded as well */
    @OneToMany(mappedBy = "seller", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<Product> products;

    @OneToMany(mappedBy = "buyer")
    private Collection<Contract> contracts;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
