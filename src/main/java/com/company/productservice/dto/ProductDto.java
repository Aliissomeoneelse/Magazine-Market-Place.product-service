package com.company.productservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Integer id;
    private Integer categoryId;
    @NotBlank(message = "product name  cannot be null or empty")
    private String prodName;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double prodMass;
    private Double amount;
    private Integer imageId;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
