package com.petvacay.dto.order;

import com.petvacay.dto.pet.PetGeneralInfoDTO;
import com.petvacay.dto.user.UserNameDTO;
import com.petvacay.entities.Feedback;
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
public class OrderDTO {

    private long orderId;

    private String comment;

    private Timestamp startDate;

    private Timestamp endDate;

    private UserNameDTO customer;

    private UserNameDTO performer;

    private String orderStatus;

    private Feedback feedback;

    private List<PetGeneralInfoDTO> pets;
}
