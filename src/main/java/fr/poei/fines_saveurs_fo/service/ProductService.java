package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();

    Optional<Product> getById(Long id);
}
