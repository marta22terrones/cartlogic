package com.API.testAPI.controller;

import com.API.testAPI.model.Product;
import com.API.testAPI.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private IProductService productService;
    @GetMapping("")
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return productService.get(id);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void detele(@PathVariable Integer id) {
        productService.detele(id);
    }

    @DeleteMapping("/stock/{id}")
    public void deleteStock(@PathVariable Integer id) {

        Optional<Product> optionalProduct = productService.get(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if (product.getStock() == 1) {
                productService.detele(id);
            } else if (product.getStock() > 1) {
                product.setStock(product.getStock() - 1);
                productService.save(product);
            }
        }

    }

}
