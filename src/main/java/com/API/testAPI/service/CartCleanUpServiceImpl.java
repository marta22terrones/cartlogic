package com.API.testAPI.service;

import com.API.testAPI.model.Cart;
import com.API.testAPI.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartCleanUpServiceImpl implements ICartCleanUpService {

    @Autowired
    private ICartRepository cartRepository;

    @Override
    @Scheduled(fixedRate = 600000)
    public void cleanupInactiveCarts() {

        System.out.println("Executing cleanupInactiveCarts...");
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);

        List<Cart> inactiveCarts = cartRepository.findByLastActivityBefore(threshold);

        for (Cart cart : inactiveCarts) {
            cartRepository.delete(cart);
        }
    }
}
