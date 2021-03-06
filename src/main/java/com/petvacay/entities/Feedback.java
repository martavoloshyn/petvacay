package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    @Column(length = 1000)
    private String text;

    @Column
    private int stars;

    @Column
    private Timestamp date;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;
}
