package com.company.productservice.service.mapper;

import com.company.productservice.dto.ProductBaseDto;
import com.company.productservice.module.ProductBase;


import org.springframework.stereotype.Component;

@Component

public abstract class ProductBaseMapper {
    public abstract ProductBaseDto toDto(ProductBase productBase);
    public abstract ProductBase toEntity(ProductBaseDto dto);
}