package com.codegym.service.impl;

import com.codegym.model.Role;
import com.codegym.repository.RoleRepository;
import com.codegym.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;


    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }
}
