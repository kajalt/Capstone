package com.capstone.productservice.controllers;

import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("productservicestub")
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
        Product product = productService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("called by", "smart frontend"); // comes for right as well as wrong input val
//        try {
//            if (productId < 1) {
//                headers.add("called by", "wrong frontend");
//                throw new IllegalArgumentException("id is invalid");
//            }
//            Product product = productService.getProduct(productId);
//            return new ResponseEntity<>(product, headers, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody ProductDTO productDto) {
        return productService.createProduct(new Product(productDto));
    }
    @PutMapping("{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDto) {
        return productService.replaceProduct(id, this.getProduct(productDto.getId()).getBody());
    }
}
