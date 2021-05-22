package com.petvacay.repositories;

import com.petvacay.entities.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {
    MediaType findByType(String type);
}
