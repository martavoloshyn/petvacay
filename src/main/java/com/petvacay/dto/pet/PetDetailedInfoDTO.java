package com.petvacay.dto.pet;

import com.petvacay.entities.PetSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDetailedInfoDTO {

    private long petId;

    private String type;

    private String name;

    private boolean isPedigreed;

    private String breed;

    private boolean needWalking;

    private boolean needSpecialCare;

    private Timestamp birthDate;

    private boolean isExotic;

    private PetSize petSize;
}
