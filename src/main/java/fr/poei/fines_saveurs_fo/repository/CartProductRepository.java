package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.CartProductId;
import fr.poei.fines_saveurs_fo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {

    public List<CartProduct> findCartProductsByCart(Cart cart);

    public List<CartProduct> findCartProductsByCartAndProduct(Cart cart, Product product);

    @Transactional
    public int deleteCartProductByCartAndProduct(Cart cart, Product product);
}
