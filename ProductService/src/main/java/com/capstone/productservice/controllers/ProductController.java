package com.capstone.productservice.controllers;

import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return null;
    }

    @GetMapping("products/{id}")
    public ProductDTO getProductById(@PathVariable long id) {
        Product product = productService.getProduct(id);
        return new ProductDTO(product);
    }

    @PostMapping("/products/create")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productDTO;
    }
}
