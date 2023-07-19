package com.company.productservice.service;

import com.company.productservice.dto.ErrorDto;
import com.company.productservice.dto.ProductBaseDto;
import com.company.productservice.dto.ProductDto;
import com.company.productservice.dto.ResponseDto;
import com.company.productservice.module.Product;
import com.company.productservice.repository.ProductRepository;
import com.company.productservice.service.mapper.ProductMapper;

import com.company.productservice.service.validation.ProductValidation;

import com.company.productservice.repository.ProductRepositoryImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductRepositoryImpl productRepositoryImpl;
    private final ProductValidation productValidation;

    public ResponseDto<ProductDto> createProduct(ProductDto dto){
        List<ErrorDto> errors = productValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }
        try {
            Product product = productMapper.toEntity(dto);
            product.setCreatedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("Product successful created!")
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductDto> get(Integer id) {
        Optional<Product> optional = this.productRepository.findByIdAndDeletedAtIsNull(id);
        if(optional.isEmpty()){
            return ResponseDto.<ProductDto>builder()

                    .success(false)
                    .message("gay")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<ProductDto>builder()
                .success(true)
                .message("ok")
                .data(this.productMapper.toDto(optional.get()))
                .build();


    }
    public ResponseDto<ProductDto> update(ProductDto dto, Integer id) {
        Optional<Product> optional = productRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product is not found")
                    .code(-1)
                    .success(false)
                    .build();
        }
        try {
            Product product = productMapper.updateUsersFromDto(dto, optional.get());
            product.setId(optional.get().getId());
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("Product successful updated!")
                    .data(productMapper.toDto(product))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while updating error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }


    public ResponseDto<ProductDto> deleteProduct(Integer id) {
        Optional<Product> optional =productRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product is not found")
                    .code(-1)
                    .success(false)
                    .build();
        }
        try {
            Product product = optional.get();
            product.setDeletedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("Product was successfully deleted!")
                    .data(productMapper.toDto(product))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }
    public ResponseDto<Page<ProductDto>> getAllProducts(Map<String, String> params) {
        Page<ProductDto> user = this.productRepositoryImpl
                .getProduct(params)
                .map(this.productMapper::toDto);
        if (user.isEmpty()) {
            return ResponseDto.<Page<ProductDto>>builder()
                    .message("This params " + params + " are not found")
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .message("Ok")
                .success(true)
                .data(user)
                .build();
    }

    public ResponseDto<ProductDto>getByName(String name){
        Optional<Product> optional=this.productRepository.findByProdNameAndDeletedAtIsNull(name);
        if(optional.isEmpty()){
            ResponseDto.<ProductDto>builder()
                    .message("Prod was not found")
                    .success(false)

                    .build();
        }
        return ResponseDto.<ProductDto>builder()
                .message("Prod was found")
                .success(true)
                .data(this.productMapper.toDto(optional.get()))
                .build();
    }

    private Double totalPriceBasket(Integer basketId) {
       Double products=this.productRepository.findByBasketIdAndDeletedAtIsNull(basketId).stream().filter((p) -> {
                    if (basketId.equals(p.getBasketId()))
                        return true;
                    else return false;
                })
                .map(productMapper::toDto)
                .collect(Collectors.summingDouble(ProductDto::getSellingPrice));
        return this.productRepository.findAll().stream().filter((p) -> {
            if (basketId.equals(p.getBasketId()))
                return true;
            else return false;
        }).map(productMapper::toDto).mapToDouble(ProductDto::getSellingPrice).sum();
    }






}
