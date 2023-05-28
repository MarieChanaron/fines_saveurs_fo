package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.CustomerDto;
import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.RoleService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/customers")
@AllArgsConstructor
@Controller
public class CustomerController {

    CustomerService customerService;
    RoleService roleService;
    MapStructMapper mapStructMapper;

    // ---------------Récupérer et afficher les données du client connecté-------------
    @GetMapping
    public String customer(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isEmpty())  return "redirect:/login";
        Customer customer = customerOptional.get();
        CustomerDto customerDto = mapStructMapper.toDto(customer);
        model.addAttribute("customer", customerDto);
        return "profile";
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


}
