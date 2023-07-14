package com.company.productservice.service.validation;


import com.company.productservice.dto.ErrorDto;
import com.company.productservice.dto.ProductBaseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductBaseValidation {
    public List<ErrorDto> validate(ProductBaseDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if(dto.getProdName().length()>20){
            errors.add(new ErrorDto("prodName","prodName length is very long"));
        }
        return errors;
    }
}
