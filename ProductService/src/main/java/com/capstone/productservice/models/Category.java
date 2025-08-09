package com.capstone.productservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseModel{
    private String categoryName;
    private String categoryDescription;
    @JsonBackReference
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size = 5)
    private List<Product> products;
}
