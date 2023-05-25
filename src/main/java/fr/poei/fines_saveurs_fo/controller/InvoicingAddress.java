package fr.poei.fines_saveurs_fo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoicing-address")
public class InvoicingAddress {

    @GetMapping
    public String getInvoicingAddress() {
        return "invoicing-address";
    }
}
