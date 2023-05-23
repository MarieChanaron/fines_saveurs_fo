package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.repository.CartProductRepository;
import fr.poei.fines_saveurs_fo.repository.CartRepository;
import fr.poei.fines_saveurs_fo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {

    final CartRepository cartRepository;
    final CartProductRepository cartProductRepository;

    public CartService(ProductRepository productRepository, CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    public Cart saveCart(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        return savedCart;
    }

    public CartProduct saveLineItems(CartProduct lineItems) {
        return cartProductRepository.save(lineItems);
    }

    public List<CartProduct> findAllCartItems(Cart cart) {
        return cartProductRepository.findCartProductsByCart(cart);
    }

}
