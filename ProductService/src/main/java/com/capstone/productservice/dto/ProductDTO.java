package com.capstone.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String imageUrl;
    private CategoryDTO category;
    private Long id;
}
