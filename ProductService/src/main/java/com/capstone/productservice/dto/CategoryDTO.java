package com.capstone.productservice.dto;

import com.capstone.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    public CategoryDTO(){}
    public CategoryDTO(Category category){
        this.categoryDescription = category.getCategoryDescription();
        this.categoryName = category.getCategoryName();
        this.id = category.getId();
    }

    private String categoryName;
    private String categoryDescription;
    private Long id;

    private List<ProductDTO> products;
}
