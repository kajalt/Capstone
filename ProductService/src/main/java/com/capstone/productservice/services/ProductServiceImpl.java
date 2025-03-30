package com.capstone.productservice.services;

import com.capstone.productservice.dto.FakeStoreProductDto;
import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,productId).getBody();
        if (fakeStoreProductDto != null) {
            return getProduct(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        return null;
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setProductName(fakeStoreProductDto.getTitle());
        product.setProductPrice(fakeStoreProductDto.getPrice());
        product.setProductDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}