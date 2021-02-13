package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pricingId;

    @Column
    private Timestamp date;

    @Column(length = 1000)
    private String comment;

    @Column
    private int bigPetBonus;

    @Column
    private int mediumPetBonus;

    @Column
    private int isPedigreedBonus;

    @Column
    private int needWalkingBonus;

    @Column
    private int needSpecialCareBonus;

    @Column
    private int exoticPetBonus;
}
