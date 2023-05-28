package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Category;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.repository.CartProductRepository;
import fr.poei.fines_saveurs_fo.repository.ProductRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl underTest;
    @Mock
    private CategoryService categoryServiceMock;
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CartProductRepository cartProductRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void ShouldReturnAllProductsGivenKeywords() {
        String keywords = "keyword1 keyword2";
        String[] keywordsArray = keywords.split(" ");
        List<Product> expectedProducts1 = new ArrayList<>();
        expectedProducts1.add(new Product());
        List<Product> expectedProducts2 = new ArrayList<>();
        expectedProducts2.add(new Product());
        expectedProducts2.add(new Product());

        when(productRepositoryMock.search("keyword1")).thenReturn(expectedProducts1);
        when(productRepositoryMock.search("keyword2")).thenReturn(expectedProducts2);

        List<Product> result = underTest.searchByKeywords(keywords);

        assertEquals(3, result.size()); // Check if the result contains all the expected products
        verify(productRepositoryMock, times(2)).search(anyString()); // Verify that the productRepositoryMock's search method was called twice
    } //searchByKeywords

    @Test
    public void ShouldReturnAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());

        when(productRepositoryMock.findAll()).thenReturn(expectedProducts);

        List<Product> result = underTest.getAllProduct();

        assertEquals(expectedProducts, result);
    } // getAllProduct

    @Test
    public void ShouldReturnOptProductGivenId() {
        Long id = 1L;
        Optional<Product> expectedProduct = Optional.of(new Product());
        expectedProduct.get().setId(id);

        when(productRepositoryMock.findById(id)).thenReturn(expectedProduct);

        Optional<Product> result = underTest.getById(id);

        assertEquals(expectedProduct, result);
    } // getById

    @Test
    public void ShouldReturnAllProductsGivenCategoryId() {
        long categoryId = 1L;
        Optional<Category> category = Optional.of(new Category());
        category.get().setId((int) categoryId);
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());

        when(categoryServiceMock.fetchById(categoryId)).thenReturn(category);
        when(productRepositoryMock.findAllByCategory(category.get())).thenReturn(expectedProducts);

        List<Product> result = underTest.fetchProductsByCategory(categoryId);

        assertEquals(expectedProducts, result);
    } // fetchProductsByCategory

    @Test
    public void ShouldUpdateStock() {
        Cart cart = new Cart();
        List<CartProduct> cartProducts = new ArrayList<>();
        CartProduct cartProduct1 = new CartProduct();
        cartProduct1.setProduct(new Product());
        cartProduct1.getProduct().setId(1L);
        cartProduct1.setQuantity((byte) 2);
        cartProducts.add(cartProduct1);
        CartProduct cartProduct2 = new CartProduct();
        cartProduct2.setProduct(new Product());
        cartProduct2.getProduct().setId(2L);
        cartProduct2.setQuantity((byte) 3);
        cartProducts.add(cartProduct2);

        when(cartProductRepositoryMock.findCartProductsByCart(cart)).thenReturn(cartProducts);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(new Product()));
        when(productRepositoryMock.findById(2L)).thenReturn(Optional.empty());

        underTest.updateStock(cart);

        verify(cartProductRepositoryMock, times(1)).findCartProductsByCart(cart); // Verify that the cartProductRepositoryMock's findCartProductsByCart method was called once
        verify(productRepositoryMock, times(2)).findById(anyLong()); // Verify that the productRepositoryMock's findById method was called twice
        verify(productRepositoryMock, times(1)).updateStock(anyInt(), anyLong()); // Verify that the productRepositoryMock's updateStock method was called once
    } // updateStock


}