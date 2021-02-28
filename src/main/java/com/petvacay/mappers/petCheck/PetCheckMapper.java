package com.petvacay.mappers.petCheck;

import com.petvacay.dto.petCheck.PetCheckDto;
import com.petvacay.entities.PetCheck;
import com.petvacay.mappers.GeneralMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetCheckMapper implements GeneralMapper<PetCheck, PetCheckDto> {
    @Override
    public PetCheckDto convertToDto(PetCheck model) {
        return PetCheckDto.builder()
                .petCheckCategory(model.getPetCheckCategory())
                .petCheckId(model.getPetCheckId())
                .petCheckStatus(model.getPetCheckStatus())
                .request_comment(model.getRequest_comment())
                .response_comment(model.getResponse_comment())
                .build();
    }

    @Override
    public PetCheck convertToModel(PetCheckDto dto) {
        return PetCheck.builder()
                .petCheckCategory(dto.getPetCheckCategory())
                .petCheckId(dto.getPetCheckId())
                .petCheckStatus(dto.getPetCheckStatus())
                .request_comment(dto.getRequest_comment())
                .response_comment(dto.getResponse_comment())
                .build();
    }

    public List<PetCheck> convertListToModel(List<PetCheckDto> petCheckDtos) {
        List<PetCheck> petCheckModels = new ArrayList<>();
        petCheckDtos.forEach((petCheckDto) -> petCheckModels.add(convertToModel(petCheckDto)));
        return petCheckModels;
    }

    public List<PetCheckDto> convertListToDto(List<PetCheck> petCheckModels) {
        List<PetCheckDto> petCheckDtos = new ArrayList<>();
        petCheckModels.forEach((petCheckModel) -> petCheckDtos.add(convertToDto(petCheckModel)));
        return petCheckDtos;
    }

}
