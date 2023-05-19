package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name="cart_product")
@Data @NoArgsConstructor
@IdClass(CartProductId.class)
public class CartProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;
    @Id
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    private byte quantity;

}

// Prix total : calculé. Valeur = prix (dans product) * quantité
