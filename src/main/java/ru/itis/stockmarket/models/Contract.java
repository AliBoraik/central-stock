package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue
    private UUID innerId;

    @Column(updatable = false)
    @Builder.Default
    private Date contractDate = new Date();

    private Date paymentDate;

    private Date deliveryDate;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Organization buyer;

    private double count;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;

}
