package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) { return productRepository.findById(id);}

}
