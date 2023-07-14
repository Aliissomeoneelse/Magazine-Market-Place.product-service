package com.company.productservice.controller;


import com.company.productservice.dto.ProductBaseDto;
import com.company.productservice.dto.ResponseDto;
import com.company.productservice.service.ProductBaseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("productbase")
@RequiredArgsConstructor
public class ProductBaseController {
    private final ProductBaseService productBaseService;

    @PostMapping("/create")
    @Operation(
            tags = "Product base"
    )
    public ResponseDto<ProductBaseDto> create(@RequestBody ProductBaseDto dto){
        return productBaseService.create(dto);
    }

    @GetMapping(value = ("/get/{id}"))
    @Operation(
            tags = "Product base"
    )
    public ResponseDto<ProductBaseDto> get (@PathVariable("id") Integer id) {
        return productBaseService.get(id);
    }

    @GetMapping(value = ("/get-all"))
    @Operation(
            tags = "Product base"
    )
    public ResponseDto<Page<ProductBaseDto>> getAll(@RequestParam Map<String, String> params) {
        return productBaseService.getAll(params);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(
            tags = "Product base"
    )
    public ResponseDto<ProductBaseDto> update(@PathVariable("id") Integer id, @RequestBody ProductBaseDto dto) {
        return productBaseService.update(dto, id);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    @Operation(
            tags = "Product base"
    )
    public ResponseDto<ProductBaseDto> delete(@PathVariable("id") Integer id) {
        return productBaseService.delete(id);
    }
}
