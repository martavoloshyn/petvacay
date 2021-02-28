package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "performers")
public class Performer extends User {

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String building;

    @Column
    private String apartment;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(length = 1000)
    private String aboutInfo;

    @Column
    private String cardNumber;

    @Column(nullable = false)
    private Timestamp birthDate;

    @OneToMany(mappedBy = "performer")
    private List<Order> orders;

    @OneToMany(mappedBy = "performer")
    private List<Vacation> vacations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pricing_id")
    private Pricing pricing;

    @ManyToMany
    @JoinTable(name = "performer_works_with_category",
            joinColumns = @JoinColumn(name = "performer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Performer(User user) {
        super(user.userId, user.email, user.password, user.firstName, user.lastName, user.role, user.userStatus);
    }
}
