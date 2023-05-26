package fr.poei.fines_saveurs_fo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/confirmation")
public class ConfirmationController {

    @GetMapping
    public String confirmation() {
        return "confirmation";
    }
}