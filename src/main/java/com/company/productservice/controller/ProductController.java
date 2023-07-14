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

    @GetMapping("/get")
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> get (@RequestParam Integer id) {
        return productService.get(id);
    }

    @GetMapping("/getAll")
    @Operation(
            tags = "Product"
    )
    public ResponseDto<Page<ProductDto>> getAll(@RequestParam Map<String, String> params) {
        return productService.getAllProducts(params);
    }

    @PutMapping("/update")
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> update(@RequestParam Integer id, @RequestBody ProductDto dto) {
        return productService.updateProduct(dto, id);
    }

    @DeleteMapping("/delete")
    @Operation(
            tags = "Product"
    )
    public ResponseDto<ProductDto> delete(@RequestParam("id") Integer id) {
        return productService.deleteProduct(id);
    }
}