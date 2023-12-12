package com.API.testAPI.service;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.CartDetail;
import com.API.testAPI.model.Product;
import com.API.testAPI.repository.ICartRepository;
import com.API.testAPI.service.impl.CartCleanUpServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestCartCleanUpService {

    @InjectMocks
    private CartCleanUpServiceImpl cartCleanUpService;

    @Mock
    private ICartRepository cartRepository;

    @Test
    void testCleanupInactiveCarts() {

        Cart inactiveCart = createCartWithDetailsAndInactiveActivity();

        when(cartRepository.findByLastActivityBefore(any())).thenReturn(Collections.singletonList(inactiveCart));

        cartCleanUpService.cleanupInactiveCarts();

        verify(cartRepository).delete(inactiveCart);
    }

    public static Cart createCartWithDetailsAndInactiveActivity() {
        Cart cart = new Cart();
        cart.setLastActivity(LocalDateTime.now().minusMinutes(15));

        Product product1 = new Product(1L, "Producto 1", 10.0, 100);
        Product product2 = new Product(2L, "Producto 2", 20.0, 50);

        CartDetail cartDetail1 = new CartDetail(1L, cart, product1, 3);
        CartDetail cartDetail2 = new CartDetail(2L, cart, product2, 2);

        cart.addCartDetail(cartDetail1);
        cart.addCartDetail(cartDetail2);

        return cart;
    }
}
