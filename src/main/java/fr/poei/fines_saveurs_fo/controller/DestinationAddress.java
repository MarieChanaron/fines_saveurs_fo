package fr.poei.fines_saveurs_fo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/destination-address")
public class DestinationAddress {

    @GetMapping
    public String getDestinationAddress(HttpSession session) {

        return "destination-address";
    }
}
