package com.API.testAPI.model.dto;


import java.util.List;

public class CartItemInfoDTO {

    private String description;
    private double price;
    private int quantity;

    public CartItemInfoDTO() { }

    public CartItemInfoDTO(String description, double price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public CartItemInfoDTO(List<CartItemInfoDTO> cartItemInfoList) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
