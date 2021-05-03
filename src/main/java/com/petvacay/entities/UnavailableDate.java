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
@Table(name = "unavailable_dates")
public class UnavailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long unavailableDateId;

    @Column
    private Timestamp startDate;

    @Column
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private Performer performer;
}
