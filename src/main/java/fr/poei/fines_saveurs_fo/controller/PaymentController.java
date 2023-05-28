package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.Order;
import fr.poei.fines_saveurs_fo.service.AddressService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
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
    final CustomerService customerService;
    final AddressService addressService;


    @GetMapping
    public String payment(HttpSession session) {

        // Check that the order has been confirmed by clicking on the button
        if (session.getAttribute("orderConfirmed") == null) {
            return "redirect:/order";
        }

        Optional<Customer> customerOptional = customerService.fetchByEmail(
                (String) session.getAttribute("email")
        );
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();

        // Check that we have a destination address
        Address destinationAddress = addressService.getDestinationAddress(customer);
        if (destinationAddress.getId() == 0) {
            return "redirect:/order/destination-address";
        }

        // Check that we have an invoicing address
        Address invoicingAddress = addressService.getInvoicingAddress(customer);
        if (invoicingAddress.getId() == 0) {
            return "redirect:/order/invoicing-address";
        }

        return "payment";
    }


    @PostMapping
    public String validateOrder(HttpSession session) {

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
        return "redirect:/order/confirmation";
    }
}
