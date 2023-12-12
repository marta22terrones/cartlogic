package com.API.testAPI.controller;

import com.API.testAPI.model.Cart;
import com.API.testAPI.service.ICartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestProductController {
    @Mock
    private ICartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Cart expectedCart = new Cart();
        when(cartService.save(any(Cart.class))).thenReturn(expectedCart);

        Cart result = cartController.save(new Cart());

        assertEquals(expectedCart, result);
        verify(cartService, times(1)).save(any(Cart.class));
    }

    @Test
    void testDelete() {
        Cart cart = new Cart();
        cartController.detele(cart);

        verify(cartService, times(1)).delete(cart);
    }

    @Test
    void testAddProductToNewCart() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("productId", 1);
        payload.put("quantity", 2);

        ResponseEntity<String> result = cartController.addProductToNewCart(payload);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Product added to a new cart successfully.", result.getBody());
        verify(cartService, times(1)).addProductToNewCart(1, 2);
    }

    @Test
    void testAddProductToCart() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("productId", 1);
        payload.put("quantity", 2);

        ResponseEntity<String> result = cartController.addProductToCart(1L, payload);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Product added to cart successfully.", result.getBody());
        verify(cartService, times(1)).addProductToCart(1L, 1, 2);
    }
}
