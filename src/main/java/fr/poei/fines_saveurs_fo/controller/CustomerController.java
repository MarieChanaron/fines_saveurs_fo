package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.RoleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/customers")
@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    RoleService roleService;
    // ---------------Récupérer et afficher les données du client connecté-------------
    @GetMapping
    public String customer(Model model, HttpSession session) {
        Customer customer = customerService.fetchByEmail(String.valueOf(session.getAttribute("email"))).get();
        if (customer != null) {
            model.addAttribute("customers", customer);
            return "customer-profile";
        } else {
            return "redirect:/login";
        }
    }

    // ----------------------------Modifier les données du client----------------
    @GetMapping("edit")
    public String editGet(Model model, HttpSession session) {
        Customer customer = customerService.fetchByEmail(String.valueOf(session.getAttribute("email"))).get();
        model.addAttribute("customers", customer);
        return "edit-customer";
    }

    @PostMapping("edit")
    public String editPost(@ModelAttribute("customer") Customer customer, Model model, HttpSession session) {
        Customer connectedCustomer = customerService.fetchByEmail(String.valueOf(session.getAttribute("email"))).get();
        connectedCustomer.setFirstname(customer.getFirstname());
        connectedCustomer.setLastname(customer.getLastname());
        customerService.save(connectedCustomer);
        model.addAttribute("customer", connectedCustomer);
        return "redirect:/customers";
    }

    //-------------------- Inscription du client/utilisateur--------------------------------
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, @ModelAttribute("customer") Customer customer) {
        Optional<Role> customerRole = roleService.findById(4);
        customer.setRole(customerRole .orElse(null));
        customerService.save(customer);
        return "redirect:/login";
    }
}
