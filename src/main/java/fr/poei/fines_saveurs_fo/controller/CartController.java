package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.CartProductDto;
import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.controller.dto.ProductDto;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.service.CartService;
import fr.poei.fines_saveurs_fo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CartController {

    final CartService cartService;
    final ProductService productService;
    final MapStructMapper mapStructMapper;


    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam int id, @RequestParam byte qty, HttpSession session) {

        Cart cart;

        if (session.getAttribute("cart") == null) {
            Cart newCart = new Cart();
            newCart.setCreatedAt(LocalDateTime.now());
            cart = cartService.saveCart(newCart);
            session.setAttribute("cart", cart);
        } else {
            cart = (Cart) session.getAttribute("cart");
        }

        CartProduct lineItem = null;
        Optional<Product> productOptional = productService.getById((long) id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            // Search an instance of CartProduct by product and by cart
            List<CartProduct> lineItems = cartService.findLineItemsByCartAndProduct(cart, product);
            if (lineItems.size() == 1) { // If an instance of CartProduct exists
                lineItem = lineItems.get(0);
                byte quantity = lineItem.getQuantity();
                quantity += qty; // set the quantity
                lineItem.setQuantity(quantity);
            } else {
                lineItem = new CartProduct(); // create a new line item
                lineItem.setCart(cart);
                lineItem.setProduct(product);
                lineItem.setQuantity(qty);
            }
        }
        cartService.saveLineItems(lineItem); // saves if doesn't exist or updates
        return "redirect:/cart";
    }

    @GetMapping("cart")
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
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }
}