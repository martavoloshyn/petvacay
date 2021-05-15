package com.petvacay.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameDTO {

    private long userId;

    private String firstName;

    private String lastName;

    private boolean isInfoFilled;
}
