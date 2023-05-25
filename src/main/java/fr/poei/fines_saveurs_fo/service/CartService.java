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

    public CartProduct saveLineItems(CartProduct lineItems) {
        return cartProductRepository.save(lineItems);
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
