package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Admin;
import fr.poei.fines_saveurs_fo.service.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class  AdminController {
    private AdminServiceImpl adminService;

    @GetMapping("/admins")
//    @ResponseBody
    public String getAllAdmin(Model model) {
        List<Admin> admins = adminService.getAllAdmin();
        model.addAttribute("listAdmins", admins);
        model.addAttribute("newAdmin", new Admin());
        return "admin";
    }
    @PostMapping("/admins")
    public String saveAdmin (@ModelAttribute("newAdmin") Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        adminService.deleteById(id);
        return "redirect:/admins";
    }
}
