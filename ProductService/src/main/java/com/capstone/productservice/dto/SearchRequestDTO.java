package com.capstone.productservice.dto;


import com.capstone.productservice.models.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;

    private int pageNumber;

    private int pageSize;

    private List<SortParam> sortParamList = new ArrayList<>();
}