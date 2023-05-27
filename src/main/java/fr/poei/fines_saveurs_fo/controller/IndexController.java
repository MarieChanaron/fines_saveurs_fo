package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Category;
import fr.poei.fines_saveurs_fo.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

    final CategoryService categoryService;

    @GetMapping
    public String index(HttpSession session, Model model) {
        List<Category> categories = categoryService.fetchAllCategories();
        // model.addAttribute("categories", categories);
        session.setAttribute("categories", categories);
        return "redirect:/products";
    }
}