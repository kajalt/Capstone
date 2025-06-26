package com.capstone.productservice.models;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
