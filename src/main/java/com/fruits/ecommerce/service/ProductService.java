package com.fruits.ecommerce.service;

import com.fruits_openapi.ecommerce_openapi.model.ProductCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDto> getAllProducts(Long pageNo, Long pageSize);
    Map<String, List<Object>> productsBulkCreation(List<ProductCreationDto> productCreationDtos);
}
