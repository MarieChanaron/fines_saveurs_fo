package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public String order(HttpSession session, Model model) {
        session.removeAttribute("redirect");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getCustomer() == null) { // If the customer is not logged in
            session.setAttribute("redirect", "order");
            return "redirect:/login";
        } else {
            return "order";
        }

    }
}
