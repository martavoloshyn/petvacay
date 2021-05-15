package com.petvacay.mappers.pet;

import com.petvacay.dto.pet.NewPetDTO;
import com.petvacay.entities.Pet;
import com.petvacay.mappers.GeneralMapper;
import com.petvacay.repositories.PetSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewPetMapper implements GeneralMapper<Pet, NewPetDTO> {

    private PetSizeRepository petSizeRepository;

    @Autowired
    public NewPetMapper(PetSizeRepository petSizeRepository) {
        this.petSizeRepository = petSizeRepository;
    }

    @Override
    public NewPetDTO convertToDto(Pet model) {
        return null;
    }

    @Override
    public Pet convertToModel(NewPetDTO dto) {
        return Pet.builder()
                .type(dto.getType())
                .name(dto.getName())
                .isPedigreed(dto.isPedigreed())
                .breed(dto.getBreed())
                .birthDate(dto.getBirthDate())
                .isExotic(dto.isExotic())
                .needSpecialCare(dto.isNeedSpecialCare())
                .needWalking(dto.isNeedWalking())
                .petSize(petSizeRepository.getOne(dto.getPetSizeId()))
                .build();
    }

    public List<Pet> convertListToModel(List<NewPetDTO> petDtos) {
        List<Pet> petModels = new ArrayList<>();
        petDtos.forEach((petDto) -> petModels.add(convertToModel(petDto)));
        return petModels;
    }
}
