package com.fruits.ecommerce.controller;

import com.fruits.ecommerce.service.ProductService;
import com.fruits_openapi.ecommerce_openapi.api.DashboardApi;
import com.fruits_openapi.ecommerce_openapi.api.ProductsApi;
import com.fruits_openapi.ecommerce_openapi.model.BulkCreationResponse;
import com.fruits_openapi.ecommerce_openapi.model.GetProductsSuccessfulResponse;
import com.fruits_openapi.ecommerce_openapi.model.ProductCreationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController implements ProductsApi, DashboardApi {
    private final ProductService productService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ProductsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<GetProductsSuccessfulResponse> findAllProducts(Long page, Long pageSize) {
        GetProductsSuccessfulResponse response = new GetProductsSuccessfulResponse();

        response.code("4000");
        response.message("Products retrieved successfully");
        response.result(productService.getAllProducts(page,pageSize));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BulkCreationResponse> productsBulkCreation(List<@Valid ProductCreationDto> productCreationDto) {
        BulkCreationResponse response = new BulkCreationResponse();

        Map<String,List<Object>> map = productService.productsBulkCreation(productCreationDto);

        response.code("4000");
        response.message("Products bulk creation summary");
        response.createdProducts(map.get("CREATED_PRODUCTS"));
        response.notCreatedProducts(map.get("NOT_CREATED_PRODUCTS"));

        return ResponseEntity.ok(response);
    }
}
