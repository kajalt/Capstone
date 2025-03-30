package com.capstone.productservice.services;

import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(Long productId);
    ProductDTO createProduct(ProductDTO productDto);
}
