package com.capstone.productservice.services;

import com.capstone.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StorageProductService implements ProductService {

    private ProductRepository productRepo;

    public StorageProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> optionalProduct = productRepo.findProductById(productId);
        if(optionalProduct.isEmpty()) {
            return null;
        }

        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        Product resultProduct = productRepo.save(product);
        return resultProduct;
    }

    @Override
    public Product replaceProduct(Long id, ResponseEntity<Product> product) {
        return null;
    }
}