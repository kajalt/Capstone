package com.capstone.productservice.services;

import com.capstone.productservice.dto.UserDTO;
import com.capstone.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.capstone.productservice.repositories.ProductRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

//@Primary
@Service
public class StorageProductService implements ProductService {

    private ProductRepository productRepo;

    private RestTemplate restTemplate;

    public StorageProductService(ProductRepository productRepo,RestTemplate restTemplate) {
        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getProductDetails(Long productId, Long userId) {
        //RestTemplate restTemplate = new RestTemplate();
        UserDTO user = restTemplate.getForEntity("http://userservice/users/{uid}", UserDTO.class,userId).getBody();
        System.out.println("USER EMAIL = "+user.getEmail());
        if(user !=null) {
            Product product = productRepo.findProductById(productId).get();
            return product;
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> optionalProduct = productRepo.findProductById(productId);
        if (optionalProduct.isEmpty()) {
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
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}