package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserActivationRequest {

    @Id
    private long userId;

    @Column(nullable = false)
    private String activationCode;

    public UserActivationRequest(Long userId) {
        activationCode = UUID.randomUUID().toString();
        this.userId = userId;
    }
}