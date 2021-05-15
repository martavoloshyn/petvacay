package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @Column(columnDefinition = "boolean default false")
    protected boolean isInfoFilled;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    protected Role role;

    @ManyToOne
    @JoinColumn(name = "user_status_id", nullable = false)
    protected UserStatus userStatus;
}
