package com.capstone.productservice.stubs;

import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Primary
@Service("productservicestub")
public class ProductServiceStub implements ProductService {
    Map<Long, Product> productMap;

    public ProductServiceStub() {
        productMap = new HashMap<>();
    }

    @Override
    public List<Product> getAllProducts() {
        return (List)productMap.values();
    }

    @Override
    public Product getProduct(Long productId) {
        if(productMap.containsKey(productId)) {
            return productMap.get(productId);
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
        productMap.put(product.getId(),product);
        return productMap.get(product.getId());
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        productMap.put(id,product);
        return productMap.get(id);
    }
}