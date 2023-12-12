package com.API.testAPI.service;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.Product;
import com.API.testAPI.repository.ICartRepository;
import com.API.testAPI.repository.IProductRepository;
import com.API.testAPI.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestCartService {
    @Mock
    private IProductService productService;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private ICartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddProductToCart() throws Exception {

        //TODO
        Long cartId = 1L;
        Integer productId = 2;
        int quantity = 3;

        Cart cart = new Cart();
        Product product = new Product();
        product.setId(Long.valueOf(productId));
        product.setStock(5);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        cartService.addProductToCart(cartId, productId, quantity);

        assertEquals(LocalDateTime.now().getMinute(), cart.getLastActivity().getMinute());
        assertEquals(1, cart.getCartDetails().size());

        verify(cartRepository, times(1)).findById(cartId);
        verify(productRepository, times(1)).findById(productId);
        verify(productService, times(1)).detele(productId);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testSave() {
        Cart cart = new Cart();
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.save(new Cart());

        assertEquals(cart, result);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testDelete() {
        Cart cart = new Cart();
        cartService.delete(cart);

        verify(cartRepository, times(1)).delete(cart);
    }

    @Test
    void testAddProductToNewCart() {

        //TODO
        Integer productId = 2;
        int quantity = 3;

        Cart newCart = new Cart();
        Product product = new Product();
        product.setId(Long.valueOf(productId));
        product.setStock(5);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        cartService.addProductToNewCart(productId, quantity);

        System.out.println("Actual Cart Details: " + newCart.getCartDetails());
        System.out.println("Actual Cart Details Size: " + newCart.getCartDetails().size());

        if (newCart.getLastActivity() != null) {
            assertEquals(LocalDateTime.now().getMinute(), newCart.getLastActivity().getMinute());
        }
        assertEquals(1, newCart.getCartDetails().size());

        verify(productRepository, times(1)).findById(productId);
        verify(productService, times(1)).detele(productId);
        verify(cartRepository, times(1)).save(newCart);
    }
}

