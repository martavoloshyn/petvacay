package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "customers")
public class Customer extends User {

    @Column
    private String city;

    @Column(length = 1000)
    private String aboutInfo;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

    public Customer(User user) {
        super(user.userId, user.email, user.password, user.firstName, user.lastName, user.role, user.userStatus);
    }
}
