package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.service.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {


    private ProductServiceImpl productService;


    @GetMapping("/products")
    public String getAllProduct(Model model, HttpSession session, @Param("keyword") String keyword) {

        // Get cart and set customer
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && session.getAttribute("redirect") == "order") {
            return "redirect:/order";
        }

        List<Product> products = productService.getAllProduct(keyword);

        model.addAttribute("listProducts", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("newProduct", new Product());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String detailProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "/product-details";
        } else {
            return "error-page";
        }
    }
}