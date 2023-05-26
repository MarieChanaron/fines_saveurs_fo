package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.role.Role;

import fr.poei.fines_saveurs_fo.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Optional<Role> findById(long id) {

        return roleRepository.findById(id);
    }
    public Optional<Role> findByName( String name) {
        return  roleRepository.findByName(name);
    }
}
