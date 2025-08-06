package com.capstone.productservice.services;

import com.capstone.productservice.clients.FakeStore.FakeStoreApiClient;
import com.capstone.productservice.dto.FakeStoreProductDto;
import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreApiClient fakeStoreApiClient;

    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products/", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        if(fakeStoreProductDtos == null)
            return products;
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(getProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        if (productId == null) {
            throw new IllegalArgumentException("Absent productId, please pass some id");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Invalid productId, please pass some valid id");
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProduct(productId);
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto req_fsp_dto = getFakeStoreProductDto(product);
        FakeStoreProductDto fsp_dto = restTemplate.postForEntity("https://fakestoreapi.com/products", req_fsp_dto, FakeStoreProductDto.class).getBody();
        return getProduct(fsp_dto);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto req_fsp_dto = getFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> fsp_dto_response =
                putForEntity("https://fakestoreapi.com/products/{id}",
                        req_fsp_dto, FakeStoreProductDto.class, id);
        return getProduct(fsp_dto_response.getBody());
    }

    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getProductDescription());
        fakeStoreProductDto.setPrice(product.getProductPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setTitle(product.getProductName());
        if (product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getCategoryName());
        }
        return fakeStoreProductDto;
    }

    private <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setProductName(fakeStoreProductDto.getTitle());
        product.setProductPrice(fakeStoreProductDto.getPrice());
        product.setProductDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}