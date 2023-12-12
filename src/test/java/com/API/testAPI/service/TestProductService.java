package com.API.testAPI.service;

import com.API.testAPI.model.Product;
import com.API.testAPI.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestProductService {
    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        List<Product> expectedProducts = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> result = productService.getAll();

        assertEquals(expectedProducts, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGet() {
        Integer productId = 1;
        Product expectedProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        Optional<Product> result = productService.get(productId);

        assertEquals(Optional.of(expectedProduct), result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testSave() {
        Product expectedProduct = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        Product result = productService.save(new Product());

        assertEquals(expectedProduct, result);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        productService.update(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDelete() {
        Integer productId = 1;
        productService.detele(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}
