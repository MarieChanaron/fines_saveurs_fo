package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Category;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.repository.CartProductRepository;
import fr.poei.fines_saveurs_fo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;


    @Override
    public List<Product> searchByKeywords(String keywords) {
        String[] keywordsArray = keywords.split(" ");
        List<Product> products = new ArrayList<>();
        for (String keyword : keywordsArray) {
            List<Product> productsForKeyword = productRepository.search(keyword);
            products.addAll(productsForKeyword);
        }
        return products;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) { return productRepository.findById(id);}

    @Override
    public List<Product> fetchProductsByCategory(long categoryId) {
        Optional<Category> category = categoryService.fetchById(categoryId);
        List<Product> products = new ArrayList<>();
        if (category.isPresent()) products = productRepository.findAllByCategory(category.get());
        return products;
    }

    @Override
    public void updateStock(Cart cart) {
        List<CartProduct> cartProducts = cartProductRepository.findCartProductsByCart(cart);
        for (CartProduct listItem : cartProducts) {
            long itemBoughtId = listItem.getProduct().getId();
            Optional<Product> productOptional = productRepository.findById(itemBoughtId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                int newStock = product.getStock() - listItem.getQuantity();
                productRepository.updateStock(newStock, product.getId());
            } else {
                System.out.println("Product not found. List item: " + listItem);
            }
        }
    }
}
