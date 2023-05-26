package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SignupController {
    @Autowired
    CustomerService customerService;
    @Autowired
    RoleService roleService;

    //-------------------- Inscription du client/utilisateur--------------------------------
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, @ModelAttribute("customer") Customer customer) {
        Optional<Role> customerRole = roleService.findByName("USER");
        if (customerRole.isEmpty()) return "404";
        customer.setRole(customerRole .orElse(null));
        customerService.registerNewCustomer(customer);
        return "redirect:/login";
    }
}
