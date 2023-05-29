package fr.poei.fines_saveurs_fo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class otherPageController {

    @GetMapping("/mentions-legales")
    public String afficherMentionsLegales() {
        return "mentions-legales";
    }

}
