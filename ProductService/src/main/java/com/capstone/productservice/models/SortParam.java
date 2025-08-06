package com.capstone.productservice.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SortParam {
    private String paramName;

    private SortType sortType;
}