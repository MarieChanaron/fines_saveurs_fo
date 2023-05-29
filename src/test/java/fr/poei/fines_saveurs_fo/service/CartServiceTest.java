package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.CartProduct;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.Product;
import fr.poei.fines_saveurs_fo.repository.CartProductRepository;
import fr.poei.fines_saveurs_fo.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CartServiceTest {

    @InjectMocks
    private CartService underTest;

    @Mock
    private CartRepository cartRepositoryMock;

    @Mock
    private CartProductRepository cartProductRepositoryMock;

    @Mock
    private ProductService productServiceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ShouldSaveCart() {
        // Arrange
        Cart cart = new Cart();
        when(cartRepositoryMock.save(Mockito.any(Cart.class))).thenReturn(cart);

        // Act
        Cart result = underTest.saveCart(cart);

        // Assert
        assertEquals(cart, result);
        verify(cartRepositoryMock, times(1)).save(cart);
    } // saveCart

    @Test
    void ShouldReturnCartProductForExistingLineItem() {
        Cart cart = new Cart();
        long productId = 1;
        byte qty = 2;
        Product product = new Product();
        CartProduct lineItem = new CartProduct();
        lineItem.setQuantity((byte) 3);
        List<CartProduct> lineItems = new ArrayList<>();
        lineItems.add(lineItem);

        when(productServiceMock.getById(productId)).thenReturn(Optional.of(product));
        when(cartProductRepositoryMock.findCartProductsByCartAndProduct(cart, product)).thenReturn(lineItems);
        when(cartProductRepositoryMock.save(Mockito.any(CartProduct.class))).thenReturn(lineItem);

        CartProduct result = underTest.saveLineItem(cart, productId, qty);

        assertEquals(lineItem, result);
        assertEquals(5, lineItem.getQuantity());
        verify(productServiceMock, times(1)).getById(productId);
        verify(cartProductRepositoryMock, times(1)).findCartProductsByCartAndProduct(cart, product);
        verify(cartProductRepositoryMock, times(1)).save(lineItem);
    } // saveLineItem

    @Test
    void ShouldReturnCartProductForNewLineItem() {
        Cart cart = new Cart();
        long productId = 1;
        byte qty = 2;
        Product product = new Product();
        List<CartProduct> lineItems = new ArrayList<>();

        when(productServiceMock.getById(productId)).thenReturn(Optional.of(product));
        when(cartProductRepositoryMock.findCartProductsByCartAndProduct(cart, product)).thenReturn(lineItems);
        when(cartProductRepositoryMock.save(any(CartProduct.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartProduct result = underTest.saveLineItem(cart, productId, qty);

        assertEquals(cart, result.getCart());
        assertEquals(product, result.getProduct());
        assertEquals(qty, result.getQuantity());
        verify(productServiceMock, times(1)).getById(productId);
        verify(cartProductRepositoryMock, times(1)).findCartProductsByCartAndProduct(cart, product);
        verify(cartProductRepositoryMock, times(1)).save(result);
    } // saveLineItem

    @Test
    void ShouldReturnAllCartProductGivenCart() {
        Cart cart = new Cart();
        List<CartProduct> cartProducts = new ArrayList<>();
        when(cartProductRepositoryMock.findCartProductsByCart(cart)).thenReturn(cartProducts);

        List<CartProduct> result = underTest.findAllCartItems(cart);

        assertEquals(cartProducts, result);
        verify(cartProductRepositoryMock, times(1)).findCartProductsByCart(cart);
    } // findAllCartItems

    @Test
    void ShouldReturnAllCartProductGivenCartAndProduct() {
        Cart cart = new Cart();
        Product product = new Product();
        List<CartProduct> cartProducts = new ArrayList<>();
        when(cartProductRepositoryMock.findCartProductsByCartAndProduct(cart, product)).thenReturn(cartProducts);

        List<CartProduct> result = underTest.findLineItemsByCartAndProduct(cart, product);

        assertEquals(cartProducts, result);
        verify(cartProductRepositoryMock, times(1)).findCartProductsByCartAndProduct(cart, product);
    } // findLineItemsByCartAndProduct

    @Test
    void ShouldDeleteLineItem() {
        Cart cart = new Cart();
        long productId = 1;
        Product product = new Product();
        Optional<Product> productOptional = Optional.of(product);

        when(productServiceMock.getById(productId)).thenReturn(productOptional);

        underTest.deleteLineItem(cart, productId);

        verify(productServiceMock, times(1)).getById(productId);
        verify(cartProductRepositoryMock, times(1)).deleteCartProductByCartAndProduct(cart, product);
    } // deleteLineItem

    @Test
    void ShouldSetCustomer() {
        Customer customer = new Customer();
        long cartId = 1;

        underTest.setCustomer(customer, cartId);

        verify(cartRepositoryMock, times(1)).setCustomer(customer, cartId);
    } // setCustomer
}