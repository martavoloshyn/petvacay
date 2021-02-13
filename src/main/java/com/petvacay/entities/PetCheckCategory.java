package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_check_categories")
public class PetCheckCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petCheckCategoryId;

    @Column(nullable = false)
    private String petCheckCategoryName;
}
