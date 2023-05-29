package fr.poei.fines_saveurs_fo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAuthController {

    @GetMapping("/login")
    public String loginForm (Model model) {
        return "login";
    }
}
