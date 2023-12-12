package com.API.testAPI.service;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.CartDetail;
import com.API.testAPI.model.Product;
import com.API.testAPI.repository.ICartRepository;
import com.API.testAPI.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        cartRepository.save(cart);

    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
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
        cartRepository.save(newCart);
        }


    }

