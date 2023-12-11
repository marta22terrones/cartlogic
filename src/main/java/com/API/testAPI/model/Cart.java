package com.API.testAPI.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();

    // Constructor
    public Cart() {
        // Initialize cartItems, so our API can not throw NullPointerException
        this.cartDetails = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartDetail> getCartItems() {
        return cartDetails;
    }

    public void setCartItems(List<CartDetail> cartItems) {
        this.cartDetails = cartDetails;
    }
}
