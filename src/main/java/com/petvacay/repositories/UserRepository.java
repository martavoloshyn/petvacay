package com.petvacay.repositories;

import com.petvacay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);

    User findByEmail(String email);

    boolean existsUserByEmail(String email);

    User getUserByUserId(long userId);
}
