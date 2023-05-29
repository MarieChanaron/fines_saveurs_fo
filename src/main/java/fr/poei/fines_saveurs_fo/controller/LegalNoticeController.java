package fr.poei.fines_saveurs_fo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LegalNoticeController {

    @GetMapping("/mentions-legales")
    public String displayLegalNotice() {
        return "mentions-legales";
    }
}
