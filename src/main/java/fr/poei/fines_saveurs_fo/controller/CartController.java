package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.CartProductDto;
import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.controller.dto.ProductDto;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.service.CartService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    final CartService cartService;
    final ProductService productService;
    final MapStructMapper mapStructMapper;
    final CustomerService customerService;

    @GetMapping("/add")
    public String addToCart(@RequestParam long id, @RequestParam byte qty, HttpSession session, Model model) {

        Cart cart;

        if (session.getAttribute("cart") == null) {
            Cart newCart = new Cart();
            newCart.setCreatedAt(LocalDateTime.now());

            Optional<Customer> customerOptional = customerService.fetchByEmail(
                    (String) session.getAttribute("email"));
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                newCart.setCustomer(customer);
            }

            cart = cartService.saveCart(newCart);
            session.setAttribute("cart", cart);
        } else {
            cart = (Cart) session.getAttribute("cart");
        }

        cartService.saveLineItem(cart, id, qty); // saves if doesn't exist or updates

        return "redirect:/cart";
    }

    @GetMapping
    public String cart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");

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
        model.addAttribute("totalPrice", totalPrice);

        session.setAttribute("totalQuantity", totalQuantity);
        session.removeAttribute("totalPrice");

        return "cart";
    }

    @GetMapping("/delete")
    public String deleteFromCart(@RequestParam long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        cartService.deleteLineItem(cart, id);
        return "redirect:/cart";
    }
}