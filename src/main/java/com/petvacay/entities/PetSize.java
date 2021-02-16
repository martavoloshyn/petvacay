package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_sizes")
public class PetSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petSizeId;

    @Column(nullable = false)
    private String petSizeName;
}
