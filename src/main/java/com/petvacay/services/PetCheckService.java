package com.petvacay.services;

import com.petvacay.dto.petCheck.PetCheckDTO;

import java.util.List;

public interface PetCheckService {
    List<PetCheckDTO> findPetChecks(long orderId);
}
