package com.capstone.productservice.controllers;

import com.capstone.productservice.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return null;
    }

    @GetMapping("products/{id}")
    public ProductDTO getProductById(@PathVariable long id) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("name");
        productDTO.setProductDescription("description");
        productDTO.setId(id);
        productDTO.setProductPrice(1200.0);
        return productDTO;
    }

    @PostMapping("/products/create")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productDTO;
    }
}
