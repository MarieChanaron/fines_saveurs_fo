package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.CartDto;
import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.entity.Cart;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    final MapStructMapper mapStructMapper;

    @GetMapping
    public String order(HttpSession session, Model model) {
        session.removeAttribute("redirect");
        Cart cart = (Cart) session.getAttribute("cart");
        CartDto cartDto = mapStructMapper.toDto(cart);
        System.out.println(cartDto);

        if (session.getAttribute("email") == null || session.getAttribute("email") == "") { // If the customer is not logged in
            session.setAttribute("redirect", "order");
            return "redirect:/login";
        } else {
            return "order";
        }

    }
}
