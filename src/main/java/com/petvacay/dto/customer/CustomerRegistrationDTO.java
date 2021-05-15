package com.petvacay.dto.customer;

import com.petvacay.dto.pet.NewPetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerRegistrationDTO {

    private long userId;

    private String city;

    private String aboutInfo;

    private String phoneNumber;

    private List<NewPetDTO> pets;
}
