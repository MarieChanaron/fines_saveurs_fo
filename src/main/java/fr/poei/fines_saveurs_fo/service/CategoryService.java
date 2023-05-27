package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Category;
import fr.poei.fines_saveurs_fo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> fetchById(long id) {
        return categoryRepository.findById(id);
    }
}
