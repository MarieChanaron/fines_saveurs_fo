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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    final CartService cartService;
    final ProductService productService;
    final MapStructMapper mapStructMapper;

    public CartController(CartService cartService, ProductService productService, MapStructMapper mapStructMapper) {
        this.cartService = cartService;
        this.productService = productService;
        this.mapStructMapper = mapStructMapper;
    }

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam int id, @RequestParam byte qty, HttpServletRequest request) {
        Cart cart = new Cart();
        cart.setCreatedAt(LocalDateTime.now());
        Cart savedCart = cartService.saveCart(cart);

        CartProduct lineItem = new CartProduct();
        Optional<Product> productOptional = productService.getById((long) id);
        if (productOptional.isPresent()) {
            lineItem.setCart(cart);
            Product product = productOptional.get();
            lineItem.setProduct(product);
            lineItem.setQuantity(qty);
        }
        cartService.saveLineItems(lineItem);

        request.getSession().setAttribute("cart", savedCart);

        return "redirect:/cart";
    }

    @GetMapping("cart")
    public String cart(HttpServletRequest request, Model model) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

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
