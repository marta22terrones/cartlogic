package com.API.testAPI.service;

import com.API.testAPI.model.Cart;

public interface ICartService {

    public Cart save(Cart cart);
    public void delete(Cart cart);
    public void addProductToNewCart(Integer productId, int quantity);
    public void addProductToCart(Long cartId, Integer productId, int quantity) throws Exception;
}
