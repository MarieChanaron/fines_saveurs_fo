package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.Order;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.AddressService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.OrderService;
import fr.poei.fines_saveurs_fo.service.RoleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/customers")
@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    RoleService roleService;
    @Autowired
    AddressService addressService;
    @Autowired
    OrderService orderService;


    // ---------------Récupérer et afficher les données du client connecté-------------
    @GetMapping
    public String customer(Model model, HttpSession session) {

        String email = (String) session.getAttribute("email");
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();

        List <Order> orders = orderService.fetchAllByCustomer(customer);
        Address addressLiv = addressService.getDestinationAddress(customer);
        Address addressFact = addressService.getInvoicingAddress(customer);

        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("addressFact", addressFact);
            model.addAttribute("AddressLiv", addressLiv);
            model.addAttribute("orders", orders);
            return "customer-profile";
        } else {
            return "redirect:/login";
        }

    }

    // ----------------------------Modifier les données du client----------------
    @GetMapping("edit")
    public String editGet(Model model, HttpSession session, Address addressLiv, Address addressFact) {
        Customer customer = customerService.fetchByEmail(String.valueOf(session.getAttribute("email"))).get();
        model.addAttribute("customers", customer);
        model.addAttribute("addressFact", addressFact);
        model.addAttribute("AddressLiv", addressLiv);
        return "edit-customer";
    }

    @PostMapping("edit")
    public String editPost(@ModelAttribute("customer") Customer customer,Address addressFact,Address addressLiv, Model model, HttpSession session) {
        Customer connectedCustomer = customerService.fetchByEmail(String.valueOf(session.getAttribute("email"))).get();
        connectedCustomer.setFirstname(customer.getFirstname());
        connectedCustomer.setLastname(customer.getLastname());
        customerService.save(connectedCustomer);
        model.addAttribute("customer", connectedCustomer);
        return "redirect:/customers";
    }


}
