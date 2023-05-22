package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.CartProductDto;
import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.service.CartService;
import fr.poei.fines_saveurs_fo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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
        Optional<Product> productOptional = productService.findProductById(id);
        if (productOptional.isPresent()) {
            lineItem.setCart(cart);
            Product product = productOptional.get();
            lineItem.setProduct(product);
            lineItem.setQuantity(qty);
        }
        cartService.saveLineItems(lineItem);

        request.getSession().setAttribute("cart", savedCart);
        System.out.println(request.getSession().getAttribute("cart"));
        return "redirect:/cart";
    }

    @GetMapping("cart")
    public String cart(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        // Retrieve the items from the cart
        List<CartProduct> cartItems = cartService.findAllCartItems(cart);
        List<CartProductDto> cartProductDtos = new ArrayList<>();
        for (CartProduct item : cartItems) {
            CartProductDto dto = mapStructMapper.toDto(item);
            cartProductDtos.add(dto);
        }
        System.out.println(cartProductDtos);
        return "cart";
    }
}
