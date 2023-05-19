package fr.poei.fines_saveurs_fo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data @NoArgsConstructor
public class CartProductId implements Serializable {

    private Cart cart;
    private Product product;

}