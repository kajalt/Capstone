package com.capstone.productservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel{
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String imageUrl;
    private Category category;
}
