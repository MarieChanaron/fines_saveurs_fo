package fr.poei.fines_saveurs_fo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeSessionAttributeController {

    @PostMapping("/changeSessionAttribute")
    public String changeSessionController(HttpSession session) {
        session.setAttribute("redirect", "order");
        return "redirect:/order";
    }
}
