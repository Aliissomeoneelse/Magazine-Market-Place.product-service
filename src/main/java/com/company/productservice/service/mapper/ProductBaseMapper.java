package com.company.productservice.service.mapper;
import com.company.productservice.dto.ProductBaseDto;
import com.company.productservice.module.ProductBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ProductBaseMapper {

 public abstract ProductBaseDto toDto(ProductBase productBase);


    public abstract ProductBase toEntity(ProductBaseDto dto) ;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ProductBase updateUsersFromDto(ProductBaseDto dto, @MappingTarget ProductBase product);


}