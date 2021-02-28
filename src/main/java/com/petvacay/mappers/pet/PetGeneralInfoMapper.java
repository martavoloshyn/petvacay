package com.petvacay.mappers.pet;

import com.petvacay.dto.pet.PetGeneralInfoDTO;
import com.petvacay.entities.Pet;
import com.petvacay.mappers.GeneralMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetGeneralInfoMapper implements GeneralMapper<Pet, PetGeneralInfoDTO> {

    @Override
    public PetGeneralInfoDTO convertToDto(Pet model) {
        return PetGeneralInfoDTO.builder()
                .breed(model.getBreed())
                .isPedigreed(model.isPedigreed())
                .name(model.getName())
                .type(model.getType())
                .petId(model.getPetId())
                .build();
    }

    @Override
    public Pet convertToModel(PetGeneralInfoDTO dto) {
        return Pet.builder()
                .breed(dto.getBreed())
                .isPedigreed(dto.isPedigreed())
                .name(dto.getName())
                .petId(dto.getPetId())
                .type(dto.getType())
                .build();
    }

    public List<PetGeneralInfoDTO> convertListToDto(List<Pet> petModels) {
        List<PetGeneralInfoDTO> petDtos = new ArrayList<>();
        petModels.forEach((petModel) -> petDtos.add(convertToDto(petModel)));
        return petDtos;
    }

    public List<Pet> convertListToModel(List<PetGeneralInfoDTO> petDtos) {
        List<Pet> petModels = new ArrayList<>();
        petDtos.forEach((petDto) -> petModels.add(convertToModel(petDto)));
        return petModels;
    }
}
