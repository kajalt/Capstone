package com.capstone.productservice.dto;

import com.capstone.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    public ProductDTO(){}
    public ProductDTO(Product product){
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productDescription = product.getProductDescription();
        this.imageUrl = product.getImageUrl();
        this.category = new CategoryDTO(product.getCategory());
        this.id = product.getId();
    }
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String imageUrl;
    private CategoryDTO category;
    private Long id;
}
