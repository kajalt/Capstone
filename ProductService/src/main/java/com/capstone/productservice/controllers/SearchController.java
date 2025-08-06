package com.capstone.productservice.controllers;

import com.capstone.productservice.dto.CategoryDTO;
import com.capstone.productservice.dto.ProductDTO;
import com.capstone.productservice.dto.SearchRequestDTO;
import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDTO searchRequestDto) {
        /*List<ProductDto> searchResults = new ArrayList<>();
        List<Product> products = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageSize(), searchRequestDto.getPageNumber());
        for(Product product : products) {
            searchResults.add(getProductDto(product));
        }
        return searchResults;*/

        return searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageSize(), searchRequestDto.getPageNumber(), searchRequestDto.getSortParamList());
    }

    private ProductDTO getProductDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDTO categoryDto = new CategoryDTO();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setCategoryName(product.getCategory().getCategoryName());
            categoryDto.setCategoryDescription(product.getCategory().getCategoryDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}

