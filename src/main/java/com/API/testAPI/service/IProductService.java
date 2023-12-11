package com.API.testAPI.service;

import com.API.testAPI.model.Product;

import java.util.Optional;

public interface IProductService {
    public Optional<Product> get(Integer id);
    public Product save(Product product);
    public void update(Product product);
    public void detele(Integer id);
}
