package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue
    private UUID innerId;

    private String name;
    private double price;
    private double count;
    @Builder.Default
    private double frozenCount = 0;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private ProductCatalog catalog;

    @OneToMany(mappedBy = "product")
    private List<Contract> contracts;
}
