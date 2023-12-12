package com.API.testAPI.controller;

import com.API.testAPI.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    ICartService cartService;


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
