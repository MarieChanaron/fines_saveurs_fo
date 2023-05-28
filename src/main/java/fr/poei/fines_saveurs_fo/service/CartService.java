package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.repository.CartProductRepository;
import fr.poei.fines_saveurs_fo.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CartService {

    final CartRepository cartRepository;
    final CartProductRepository cartProductRepository;
    final ProductService productService;


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public CartProduct saveLineItem(Cart cart, long id, byte qty) {
        CartProduct lineItem = null;
        Optional<Product> productOptional = productService.getById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            // Search an instance of CartProduct by product and by cart
            List<CartProduct> lineItems = this.findLineItemsByCartAndProduct(cart, product);
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
        return cartProductRepository.save(lineItem);
    }

    public List<CartProduct> findAllCartItems(Cart cart) {
        return cartProductRepository.findCartProductsByCart(cart);
    }

    public List<CartProduct> findLineItemsByCartAndProduct(Cart cart, Product product) {
        return cartProductRepository.findCartProductsByCartAndProduct(cart, product);
    }

    public void deleteLineItem(Cart cart, long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(value -> cartProductRepository.deleteCartProductByCartAndProduct(cart, value));
    }

}
