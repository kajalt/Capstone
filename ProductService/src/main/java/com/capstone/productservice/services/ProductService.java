package com.capstone.productservice.services;

import com.capstone.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);

    Product getProductDetails(Long productId, Long userId);
}
