package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_check_statuses")
public class PetCheckStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petCheckStatusId;

    @Column(nullable = false)
    private String petCheckStatusName;
}
