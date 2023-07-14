package com.company.productservice.service;

import com.company.productservice.dto.ErrorDto;
import com.company.productservice.dto.ProductBaseDto;
import com.company.productservice.dto.ResponseDto;
import com.company.productservice.module.ProductBase;
import com.company.productservice.repository.ProductBaseRepository;
import com.company.productservice.repository.ProductBaseRepositoryImpl;
import com.company.productservice.service.mapper.ProductBaseMapper;
import com.company.productservice.service.validation.ProductBaseValidation;



import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBaseService {
    private final ProductBaseMapper productBaseMapper;
    private final ProductBaseRepository productBaseRepository;
    private final ProductBaseRepositoryImpl productBaseRepositoryImpl;
    private final ProductBaseValidation productBaseValidation;

    public ResponseDto<ProductBaseDto> create(ProductBaseDto dto) {
        List<ErrorDto> errors = productBaseValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }
        try {
            ProductBase productBase = productBaseMapper.toEntity(dto);
            productBase.setCreatedAt(LocalDateTime.now());
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("Product successful created!")
                    .data(productBaseMapper.toDto(productBase))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Product while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> get(Integer id) {
        Optional<ProductBase> optional = productBaseRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Product base does not found!")
                    .code(-2)
                    .build();
        }
        return ResponseDto.<ProductBaseDto>builder()
                .success(true)
                .message("OK")
                .data(productBaseMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<Page<ProductBaseDto>> getAll(Map<String, String> params) {
        Page<ProductBaseDto> product = this.productBaseRepositoryImpl
                .getProductBase(params)
                .map(this.productBaseMapper::toDto);
        if (product.isEmpty()) {
            return ResponseDto.<Page<ProductBaseDto>>builder()
                    .message("This params " + params + " are not found")
                    .build();
        }
        return ResponseDto.<Page<ProductBaseDto>>builder()
                .message("Ok")
                .success(true)
                .data(product)
                .build();
    }

    public ResponseDto<ProductBaseDto> update(ProductBaseDto dto, Integer id) {
        Optional<ProductBase> optional = productBaseRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Product base does not found!")
                    .code(-2)
                    .build();
        }
        try {
            ProductBase productBase = productBaseMapper.toEntity(dto);
            productBase.setId(optional.get().getId());
            productBase.setUpdatedAt(LocalDateTime.now());
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("Product successful updated!")
                    .data(productBaseMapper.toDto(productBase))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Product while updating error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> delete(Integer id) {
        Optional<ProductBase> optional = productBaseRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Product base does not found!")
                    .code(-2)
                    .build();
        }
        try {
            ProductBase productBase = optional.get();
            productBase.setDeletedAt(LocalDateTime.now());
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("Product successful deleted!")
                    .data(productBaseMapper.toDto(productBase))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Product while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }
}
