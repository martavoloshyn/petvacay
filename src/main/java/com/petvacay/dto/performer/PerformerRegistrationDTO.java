package com.petvacay.dto.performer;

import com.petvacay.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformerRegistrationDTO {

    private long userId;

    private String city;

    private String street;

    private String building;

    private String apartment;

    private String phoneNumber;

    private String aboutInfo;

    private String cardNumber;

    private Timestamp birthDate;

    private List<CategoryDTO> categories;
}
