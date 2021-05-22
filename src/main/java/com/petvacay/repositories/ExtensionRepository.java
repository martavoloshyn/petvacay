package com.petvacay.repositories;

import com.petvacay.entities.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Extension findByName(String name);
}
