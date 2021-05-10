package com.petvacay.services.implementation;

import com.petvacay.entities.Role;
import com.petvacay.repositories.RoleRepository;
import com.petvacay.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getById(Long roleId) {
        return roleRepository.getOne(roleId);
    }
}
