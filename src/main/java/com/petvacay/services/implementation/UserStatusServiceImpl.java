package com.petvacay.services.implementation;

import com.petvacay.entities.UserStatus;
import com.petvacay.repositories.UserStatusRepository;
import com.petvacay.services.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    private UserStatusRepository userStatusRepository;

    @Autowired
    public UserStatusServiceImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public UserStatus findStatus(String statusName) {
        return userStatusRepository.findByUserStatusName(statusName);
    }
}
