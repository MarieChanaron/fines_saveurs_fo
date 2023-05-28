package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.*;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.service.CartService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    final CartService cartService;
    final MapStructMapper mapStructMapper;
    final CustomerService customerService;

    @GetMapping
    public String order(HttpSession session, Model model) {

            session.removeAttribute("redirect");

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart.getCustomer() == null) {
                Optional<Customer> customerOptional = customerService.fetchByEmail(
                        (String) session.getAttribute("email"));
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    CustomerDto customerDto = mapStructMapper.toDto(customer);
                    Customer customerToSave = mapStructMapper.fromDto(customerDto);
                    cart.setCustomer(customerToSave);
                    cartService.setCustomer(customerToSave, cart.getId());
                }
            }

            // Retrieve the items from the cart
            List<CartProduct> cartProducts = cartService.findAllCartItems(cart);
            List<CartProductDto> cartItems = new ArrayList<>();
            int totalQuantity = 0;
            double totalPrice = 0;

            for (CartProduct item : cartProducts) {
                ProductDto productDto = mapStructMapper.toDto(item.getProduct());
                CartProductDto lineItem = mapStructMapper.toDto(item);

                totalQuantity += lineItem.getQuantity();
                totalPrice += productDto.getPrice().doubleValue() * lineItem.getQuantity();

                lineItem.setProductDto(productDto);
                cartItems.add(lineItem);
            }

            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalQuantity", totalQuantity);
            session.setAttribute("totalPrice", totalPrice);

            return "order";

    }

}
