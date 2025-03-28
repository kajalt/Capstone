package com.capstone.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private String CategoryName;
    private String CategoryDescription;
    private Long id;

    private List<ProductDTO> products;
}
