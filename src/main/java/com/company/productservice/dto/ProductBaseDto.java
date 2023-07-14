package com.company.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBaseDto {
    private Integer id;
    Set<ProductDto> products;
    private String prodName;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double prodMass;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

