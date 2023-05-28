package fr.poei.fines_saveurs_fo.api;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.repository.CartRepository;
import fr.poei.fines_saveurs_fo.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartRestController {

    private CartService cartService;
    private CartRepository cartRepository;

    @GetMapping("/nb")
    public ResponseEntity<Integer> getNbItems(long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        int total = 0;
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<CartProduct> items = cartService.findAllCartItems(cart);
            for (CartProduct item : items) {
                total += item.getQuantity();
            }
        }
        return ResponseEntity.ok(total);
    }
}
