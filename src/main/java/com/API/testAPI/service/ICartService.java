package com.API.testAPI.service;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.dto.CartItemInfoDTO;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    public Optional<Cart> getById(Long id);
    List<CartItemInfoDTO> getCartInfoById(Long cartId);
    public Cart save(Cart cart);
    public void delete(Cart cart);
    public void deleteById(Long id);
    public void addProductToNewCart(Integer productId, int quantity);
    public void addProductToCart(Long cartId, Integer productId, int quantity) throws Exception;
    public CartItemInfoDTO getCartItemInfoById(Long cartItemId);
}
