package com.capstone.productservice.models;

import com.capstone.productservice.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel{
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String imageUrl;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public Product(){}

    public Product (ProductDTO productDto) {
        this.setId(productDto.getId());
        this.setProductName(productDto.getProductName());
        this.setProductPrice(productDto.getProductPrice());
        this.setImageUrl(productDto.getImageUrl());
        this.setProductDescription(productDto.getProductDescription());
        Category category = new Category();
        if(productDto.getCategory() != null) {
            category.setId(productDto.getCategory().getId());
            category.setCategoryName(productDto.getCategory().getCategoryName());
        }
        this.setCategory(category);
    }
}
