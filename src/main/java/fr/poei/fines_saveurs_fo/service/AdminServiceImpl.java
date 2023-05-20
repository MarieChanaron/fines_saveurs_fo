package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Admin;
import fr.poei.fines_saveurs_fo.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
//        TODO: Ajouter conditions
        return adminRepository.save(admin);
    }
    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Void deleteById(Long id) {
        adminRepository.deleteById(id);
        return null;
    }
}
