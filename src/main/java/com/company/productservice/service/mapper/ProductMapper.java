package com.company.productservice.service.mapper;


import com.company.productservice.dto.ProductDto;
import com.company.productservice.module.Product;
import org.springframework.stereotype.Component;

@Component
public  abstract class ProductMapper {
    public abstract Product toEntity(ProductDto dto);
    public abstract ProductDto toDto(Product product);


}
