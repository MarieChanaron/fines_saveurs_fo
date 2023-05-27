package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Order;
import fr.poei.fines_saveurs_fo.service.OrderService;
import fr.poei.fines_saveurs_fo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/order/payment")
public class PaymentController {

    final OrderService orderService;
    final ProductService productService;


    @GetMapping
    public String payment() {
        return "payment";
    }


    @PostMapping
    public String afterPayment(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");;
        String email = (String) session.getAttribute("email");
        double totalPrice = (Double) session.getAttribute("totalPrice");

        Optional<Order> orderSaved = orderService.saveOrder(cart, email, totalPrice);

        session.removeAttribute("cart");
        session.removeAttribute("totalPrice");

        productService.updateStock(cart);

        if (orderSaved.isEmpty()) return "404";
        return "redirect:/order/confirmation";
    }
}
