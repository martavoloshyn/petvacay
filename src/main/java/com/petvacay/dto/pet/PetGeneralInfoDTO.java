package com.petvacay.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetGeneralInfoDTO {

    private long petId;

    private String type;

    private String name;

    private boolean isPedigreed;

    private String breed;
}
