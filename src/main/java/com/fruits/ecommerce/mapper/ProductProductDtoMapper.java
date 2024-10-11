package com.fruits.ecommerce.mapper;

import com.fruits.ecommerce.domain.Product;
import com.fruits_openapi.ecommerce_openapi.model.ProductCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductProductDtoMapper {
    ProductDto productToProductDto(Product product);
    @Mapping(source = "imagePath",target = "image_path")
    Product productCreateDtoToProduct(ProductCreationDto productCreationDto);
}
