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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {


    private ProductServiceImpl productService;


    @GetMapping("/products")
    public String getAllProduct(Model model, HttpSession session, @RequestParam("cat") Optional<Long> cat) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && session.getAttribute("email") != null && session.getAttribute("redirect") == "order") {
            return "redirect:/order";
        }

        List<Product> products = new ArrayList<>();

        if (cat.isEmpty()) {
            products = productService.getAllProduct();
        } else {
            products = productService.fetchProductsByCategory(cat.get());
        }

        model.addAttribute("products", products);
        return "products";
    }


    @GetMapping("/products/search")
    public String getAllProduct(Model model, @Param("keywords") String keywords) {

        List<Product> products = productService.getAllProduct(keywords);

        model.addAttribute("products", products);
        model.addAttribute("keywords", keywords);
        return "products";
    }


    @GetMapping("/product/{id}")
    public String detailProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "/product-details";
        } else {
            return "404";
        }
    }
}