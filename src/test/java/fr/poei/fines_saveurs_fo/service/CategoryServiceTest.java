package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Category;
import fr.poei.fines_saveurs_fo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService underTest;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ShouldReturnAllCategories() {
        List<Category> expected = new ArrayList<>();

        Category category = new Category();
        category.setId(1);
        category.setName("");
        expected.add(category);
        Category category2 = new Category();
        category.setId(1);
        category.setName("");
        expected.add(category2);

        when(categoryRepositoryMock.findAll()).thenReturn(expected);

        List<Category> result = underTest.fetchAllCategories();

        assertEquals(expected, result);
    } // fetchAllCategories



    @Test
    void ShouldReturnACategory() {
        Category expected = new Category();
        expected.setName("categoryTest");
        Optional<Category> expectedOptional = Optional.of(expected);

        when(categoryRepositoryMock.findById(any(Long.class))).thenReturn(expectedOptional);

        Optional<Category> result = underTest.fetchById(1L);

        assertEquals(expectedOptional, result);
    } // fetchById

}