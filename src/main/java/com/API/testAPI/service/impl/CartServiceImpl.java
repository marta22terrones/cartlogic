package com.API.testAPI.service.impl;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.CartDetail;
import com.API.testAPI.model.Product;
import com.API.testAPI.model.dto.CartItemInfoDTO;
import com.API.testAPI.repository.ICartDetailRepository;
import com.API.testAPI.repository.ICartRepository;
import com.API.testAPI.repository.IProductRepository;
import com.API.testAPI.service.ICartService;
import com.API.testAPI.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICartDetailRepository cartDetailRepository;
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

    public List<CartItemInfoDTO> getCartInfoById(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            return cart.getCartDetails().stream()
                    .map(cartDetail -> {
                        Product product = cartDetail.getProduct();
                        return new CartItemInfoDTO(
                                product.getDescription(),
                                product.getPrice(),
                                cartDetail.getQuantity()
                        );
                    })
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
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

    public CartItemInfoDTO getCartItemInfoById(Long cartItemId) {
        CartDetail cartItem = cartDetailRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with ID: " + cartItemId));

        Product product = cartItem.getProduct();

        CartItemInfoDTO cartItemInfo = mapCartItemInfoDTO(product, cartItem);

        return cartItemInfo;
    }

    private CartItemInfoDTO mapCartItemInfoDTO(Product product, CartDetail cartDetail) {
        CartItemInfoDTO cartItemInfoDTO = new CartItemInfoDTO();
        cartItemInfoDTO.setDescription(product.getDescription());
        cartItemInfoDTO.setPrice(product.getPrice());
        cartItemInfoDTO.setQuantity(cartDetail.getQuantity());

        return cartItemInfoDTO;
    }

}

