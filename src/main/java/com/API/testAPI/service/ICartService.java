package com.API.testAPI.service;

import com.API.testAPI.model.Cart;

import java.util.Optional;

public interface ICartService {

    public Optional<Cart> getById(Long id);
    public Cart save(Cart cart);
    public void delete(Cart cart);
    public void addProductToNewCart(Integer productId, int quantity);
    public void addProductToCart(Long cartId, Integer productId, int quantity) throws Exception;
}
