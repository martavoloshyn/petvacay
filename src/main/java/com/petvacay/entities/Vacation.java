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
@Table(name = "vacations")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vacationId;

    @Column
    private Timestamp startDate;

    @Column
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private Performer performer;
}
