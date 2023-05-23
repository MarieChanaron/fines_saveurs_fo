package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    final
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional;
    }

}
