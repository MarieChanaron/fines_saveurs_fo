package fr.poei.fines_saveurs_fo.api;

import fr.poei.fines_saveurs_fo.entity.Category;
import fr.poei.fines_saveurs_fo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryRestController {

    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> allCategories() {
        List<Category> categories = categoryService.fetchAllCategories();
        return ResponseEntity.ok(categories);
    }
}
