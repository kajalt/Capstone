package com.capstone.productservice.controllers;

import com.capstone.productservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return null;
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable long id) {
        Product product = new Product();
        product.setProductName("name");
        product.setProductDescription("description");
        product.setId(id);
        product.setProductPrice(1200.0);
        return product;
    }
}
