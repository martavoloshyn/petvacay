package com.petvacay.dto.petCheck;

import com.petvacay.entities.PetCheckCategory;
import com.petvacay.entities.PetCheckStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.ManyToOne;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PetCheckDTO {

    private long petCheckId;

    private PetCheckCategory petCheckCategory;

    private PetCheckStatus petCheckStatus;

    private String request_comment;

    private String response_comment;
}
