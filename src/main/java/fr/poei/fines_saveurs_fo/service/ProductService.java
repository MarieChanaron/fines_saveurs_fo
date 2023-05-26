package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProduct(String keyword);

    List<Product> getAllProduct();

    Optional<Product> getById(Long id);


}
