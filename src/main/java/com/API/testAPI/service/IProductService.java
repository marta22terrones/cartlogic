package com.API.testAPI.service;

import com.API.testAPI.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public List<Product> getAll();
    public Optional<Product> get(Integer id);
    public Product save(Product product);
    public void update(Product product);
    public void detele(Integer id);
}
