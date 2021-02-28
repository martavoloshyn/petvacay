package com.petvacay.services;

import com.petvacay.dto.petCheck.PetCheckDto;

import java.util.List;

public interface PetCheckService {
    List<PetCheckDto> findPetChecks(long orderId);
}
