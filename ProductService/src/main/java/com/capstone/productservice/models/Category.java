package com.capstone.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category extends BaseModel{
    private String CategoryName;
    private String CategoryDescription;

    private List<Product> products;
}
