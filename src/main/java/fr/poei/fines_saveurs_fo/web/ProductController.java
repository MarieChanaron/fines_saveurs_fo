package fr.poei.fines_saveurs_fo.web;

import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductServiceImpl productService;

    @GetMapping("/products")
    public String getAllProduct(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("listProducts", products);
        model.addAttribute("newProduct", new Product());
        return "products";
    }

}
