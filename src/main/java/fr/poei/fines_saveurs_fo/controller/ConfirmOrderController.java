package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/confirm-order")
public class ConfirmOrderController {

    @PostMapping()
    public String confirmOrder(HttpSession session) {
        Object cart = session.getAttribute("cart");
        if (cart != null) {
            session.setAttribute("orderConfirmed", true);
        }
        return "redirect:/order/destination-address";
    }
}