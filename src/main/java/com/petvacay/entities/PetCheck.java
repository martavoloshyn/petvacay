package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_checks")
public class PetCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petCheckId;

    @ManyToOne
    @JoinColumn(name = "pet_check_category_id")
    private PetCheckCategory petCheckCategory;

    @ManyToOne
    @JoinColumn(name = "pet_check_status_id")
    private PetCheckStatus petCheckStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(length = 1000)
    private String request_comment;

    @Column(length = 1000)
    private String response_comment;
}
