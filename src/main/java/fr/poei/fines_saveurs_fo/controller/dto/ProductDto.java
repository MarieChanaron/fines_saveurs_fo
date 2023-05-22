package fr.poei.fines_saveurs_fo.controller.dto;

import fr.poei.fines_saveurs_fo.entity.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor
public class ProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "brand")
    private String brand;
    @Column(name = "reference", unique = true)
    private String reference;
    @Column(name = "stock")
    private int stock;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "ingredient")
    private String ingredient;
    @Column(name = "conditioning")
    private String conditioning;
    @Column(name = "origin")
    private String origin;
    @Column(name = "price")
    private BigDecimal price;
}
