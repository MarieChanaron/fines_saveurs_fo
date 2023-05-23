package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/customers")
@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping
    public String customer (Model model) {

        model.addAttribute("customers", customerService.fetchAll());
        return "customer-profile";
    }
    @GetMapping("edit/{id}")
    public String editGet (Model model, @PathVariable int id) {
        model.addAttribute("customer", customerService.fetchById(id));
        return "edit-customer";
    }
    @PostMapping("edit/{id}")
    public String editPost(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

}
