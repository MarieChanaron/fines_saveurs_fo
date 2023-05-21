package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);

    List<Admin> getAllAdmin();

    Void deleteById(Long id);
}
