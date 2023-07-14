package com.company.productservice.service.validation;

;
import com.company.productservice.dto.ErrorDto;


import com.company.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidation {


    public List<ErrorDto> validate(ProductDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if(dto.getAmount()<0){
            errors.add(new ErrorDto("amount", "Amount cannot be negative number"));
        }

        return errors;
    }
}
