package com.capstone.productservice.controllers;

import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("called by", "smart frontend"); // comes for right as well as wrong input val
        try {
            if (productId < 1) {
                headers.add("called by", "wrong frontend");
                throw new IllegalArgumentException("id is invalid");
            }
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products/create")
    public ProductDTO createProduct(@RequestBody ProductDTO productDto) {
       return productService.createProduct(productDto);
    }
}
