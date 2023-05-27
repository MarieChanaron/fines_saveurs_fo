package fr.poei.fines_saveurs_fo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/order/summary")
public class SummaryController {

    @GetMapping
    public String getSummary() {
        return "summary";
    }
}
