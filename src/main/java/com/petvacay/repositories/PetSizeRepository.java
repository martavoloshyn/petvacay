package com.petvacay.repositories;

import com.petvacay.entities.PetSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSizeRepository extends JpaRepository<PetSize, Long> {
}
