package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Order;
import fr.poei.fines_saveurs_fo.service.OrderService;
import fr.poei.fines_saveurs_fo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.tags.HtmlEscapeTag;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/order/confirmation")
public class ConfirmationController {

    OrderService orderService;
    ProductService productService;

    @GetMapping
    public String saveOrder(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");;
        String email = (String) session.getAttribute("email");
        double totalPrice = (Double) session.getAttribute("totalPrice");

        Optional<Order> orderSaved = orderService.saveOrder(cart, email, totalPrice);

        session.removeAttribute("totalQuantity");
        session.removeAttribute("cart");
        session.removeAttribute("totalPrice");
        session.removeAttribute("orderConfirmed");

        productService.updateStock(cart);

        if (orderSaved.isEmpty()) return "404";

        return "confirmation";
    }
}
