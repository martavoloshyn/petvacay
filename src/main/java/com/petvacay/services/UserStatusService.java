package com.petvacay.services;

import com.petvacay.entities.UserStatus;

public interface UserStatusService {
    UserStatus findStatus(String statusName);
}
