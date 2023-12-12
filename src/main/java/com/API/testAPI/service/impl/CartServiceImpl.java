package com.API.testAPI.service.impl;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.CartDetail;
import com.API.testAPI.model.Product;
import com.API.testAPI.repository.ICartRepository;
import com.API.testAPI.repository.IProductRepository;
import com.API.testAPI.service.ICartService;
import com.API.testAPI.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public void addProductToCart(Long cartId, Integer productId, int quantity) throws Exception {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new Exception("Cart not found which ID is: " + cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setQuantity(quantity);

        cart.addCartDetail(cartDetail);

        if (product.getStock() == quantity) {
            productService.detele(productId);
        } else if (product.getStock() > quantity) {
            product.setStock(product.getStock() - quantity);
            productService.save(product);
        }

        // Update of activy
        cart.setLastActivity(LocalDateTime.now());
        cartRepository.save(cart);

    }

    @Override
    public Optional<Cart> getById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public void addProductToNewCart(Integer productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        Cart newCart = new Cart();
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product);
        cartDetail.setQuantity(quantity);
        cartDetail.setCart(newCart);

        newCart.addCartDetail(cartDetail);

        if (product.getStock() == quantity) {
                productService.detele(productId);
            } else if (product.getStock() > quantity) {
                product.setStock(product.getStock() - quantity);
                productService.save(product);
            }

        // Update of activy
        newCart.setLastActivity(LocalDateTime.now());
        cartRepository.save(newCart);
    }

}

