package com.petvacay.dto.order;

import com.petvacay.dto.pet.PetGeneralInfoDTO;
import com.petvacay.dto.user.UserNameDTO;
import com.petvacay.entities.Customer;
import com.petvacay.entities.OrderStatus;
import com.petvacay.entities.Performer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NewOrderDto {
    private long orderId;

    private String comment;

    private Timestamp startDate;

    private OrderStatus orderStatus;

    private Timestamp endDate;

    private UserNameDTO customer;

    private UserNameDTO performer;

    private List<PetGeneralInfoDTO> pets;
}
