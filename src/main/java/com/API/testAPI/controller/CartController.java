package com.API.testAPI.controller;

import com.API.testAPI.model.Cart;
import com.API.testAPI.model.Product;
import com.API.testAPI.model.dto.CartItemInfoDTO;
import com.API.testAPI.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    ICartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartService.getById(id);
        return cart.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cartInfo/{id}")
    public ResponseEntity<List<CartItemInfoDTO>> getCartInfoById(@PathVariable Long id) {
        List<CartItemInfoDTO> cartItems = cartService.getCartInfoById(id);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping
    public Cart save(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @DeleteMapping("/{id}")
    public void detele(@PathVariable Cart cart) {
        cartService.delete(cart);
    }

    @PostMapping("/addProductToNewCart")
    public ResponseEntity<String> addProductToNewCart(@RequestBody Map<String, Object> payload) {
        Integer productId = (Integer) payload.get("productId");
        Integer quantity = (Integer) payload.get("quantity");

        cartService.addProductToNewCart(productId, quantity);

        return ResponseEntity.ok("Product added to a new cart successfully.");
    }

    @PostMapping("addProduct/{cartId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long cartId,
                                                   @RequestBody Map<String, Object> payload) throws Exception {
        Integer productId = (Integer) payload.get("productId");
        Integer quantity = (Integer) payload.get("quantity");

        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Product added to cart successfully.");
    }
}
