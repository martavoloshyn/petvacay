package com.petvacay.services.implementation;

import com.petvacay.dto.petCheck.PetCheckDTO;
import com.petvacay.mappers.petCheck.PetCheckMapper;
import com.petvacay.repositories.PetCheckRepository;
import com.petvacay.services.PetCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCheckServiceImpl implements PetCheckService {

    private PetCheckRepository petCheckRepository;
    private PetCheckMapper petCheckMapper;

    @Autowired
    public PetCheckServiceImpl(PetCheckRepository petCheckRepository, PetCheckMapper petCheckMapper) {
        this.petCheckRepository = petCheckRepository;
        this.petCheckMapper = petCheckMapper;
    }

    @Override
    public List<PetCheckDTO> findPetChecks(long orderId) {
        return petCheckMapper.convertListToDto(petCheckRepository.findPetChecksByOrderOrderId(orderId));
    }
}
