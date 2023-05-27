package fr.poei.fines_saveurs_fo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/change-session-attribute")
public class ChangeSessionAttributeController {

    @PostMapping()
    public String changeSessionController(HttpSession session) {
        session.setAttribute("redirect", "order");
        return "redirect:/order";
    }

}
