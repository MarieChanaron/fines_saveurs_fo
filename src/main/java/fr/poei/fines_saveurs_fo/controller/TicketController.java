package fr.poei.fines_saveurs_fo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class TicketController {

    @GetMapping("/ticket")
    public String displayTicketForm(Model model, HttpSession session) {

        return "ticket";
    }

}
