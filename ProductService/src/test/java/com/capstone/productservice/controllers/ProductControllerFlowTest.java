package com.capstone.productservice.controllers;

import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {

    @Autowired
    private ProductController productController;


    @Test
    public void Test_Create_Replace_GetProduct_WithStub_RunsSuccessfully() {
        //Arrange
        ProductDTO productDto = new ProductDTO();
        productDto.setId(1L);
        productDto.setProductName("Iphone 21");
        productDto.setProductDescription("Fanciest Iphone ever");


        //Act
        Product product = productController.createProduct(productDto);
        ResponseEntity<Product> productResponseEntity = productController
                .getProduct(product.getId());

        productDto.setProductName("Iphone 15");
        Product replacedProduct = productController
                .replaceProduct(product.getId(),productDto);

        ResponseEntity<Product> productResponseEntity2 = productController
                .getProduct(replacedProduct.getId());


        //Assert
        assertEquals("Iphone 14",productResponseEntity.getBody().getProductName());
        assertEquals("Iphone 15",productResponseEntity2.getBody().getProductName());
    }

}