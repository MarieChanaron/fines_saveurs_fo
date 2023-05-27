package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WabAuthController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/login")
    public String loginForm (Model model) {
        return "login";
    }

    @GetMapping("profile")
    public String displayUserProfile (Model model) {
        return "/customer-profile";
    }
}
