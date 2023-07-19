package com.company.productservice.controller;


import com.company.productservice.dto.ProductDto;
import com.company.productservice.dto.ResponseDto;
import com.company.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> create(@RequestBody ProductDto dto){
        return productService.createProduct(dto);
    }

    @GetMapping(value = ("/get/{id}"))
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> get (@PathVariable("id") Integer id) {
        return productService.get(id);
    }

    @GetMapping(value = ("/get-all"))
    @Operation(
            tags = "Product"
    )
    public ResponseDto<Page<ProductDto>> getAll(@RequestParam Map<String, String> params) {
        return productService.getAllProducts(params);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> update(@PathVariable("id") Integer id, @RequestBody ProductDto dto) {
        return productService.updateProduct(dto, id);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> delete(@PathVariable("id") Integer id) {
        return productService.deleteProduct(id);
    }
}