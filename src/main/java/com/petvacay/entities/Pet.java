package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petId;

    @Column
    private String type;

    @Column
    private String name;

    @Column
    private boolean isPedigreed;

    @Column
    private String breed;

    @Column
    private boolean needWalking;

    @Column
    private boolean needSpecialCare;

    @Column
    private Timestamp birthDate;

    @Column
    private boolean isExotic;

    @ManyToOne
    @JoinColumn(name = "pet_size_id")
    private PetSize petSize;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "pets_in_order",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;
}
