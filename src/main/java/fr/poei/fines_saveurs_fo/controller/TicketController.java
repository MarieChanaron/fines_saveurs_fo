package fr.poei.fines_saveurs_fo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TicketController {

    @GetMapping("/ticket")
    public String displayTicketForm(Model model, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        model.addAttribute("userEmail", userEmail);

        return "ticket";
    }

}
