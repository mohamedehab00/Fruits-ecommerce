package com.fruits.ecommerce.service.serviceImpl;

import com.fruits.ecommerce.Util.PagingUtil;
import com.fruits.ecommerce.domain.Product;
import com.fruits.ecommerce.mapper.ProductProductDtoMapper;
import com.fruits.ecommerce.repository.ProductRepository;
import com.fruits.ecommerce.service.ProductService;
import com.fruits_openapi.ecommerce_openapi.model.ProductCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public final ProductRepository productRepository;
    public final ProductProductDtoMapper productProductDtoMapper;

    @Override
    public List<ProductDto> getAllProducts(Long pageNo, Long pageSize) {
        Pageable pageable = PagingUtil.getPageRequest(pageNo,pageSize);

        return productRepository.findAll(pageable).getContent()
                .stream()
                .map(productProductDtoMapper::productToProductDto)
                .toList();
    }

    @Override
    public Map<String, List<Object>> productsBulkCreation(List<ProductCreationDto> productCreationDtos) {
        Map<String, List<Object>> result = new HashMap<>();

        result.put("CREATED_PRODUCTS", new ArrayList<>());
        result.put("NOT_CREATED_PRODUCTS", new ArrayList<>());

        for (ProductCreationDto dto : productCreationDtos) {
            Product product = productProductDtoMapper.productCreateDtoToProduct(dto);

            product = productRepository.save(product);

            if(product.getId() == null){
                result.get("NOT_CREATED_PRODUCTS").add(dto);
            }
            else {
                result.get("CREATED_PRODUCTS").add(product);
            }
        }
        return result;
    }
}
