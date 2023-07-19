package com.company.productservice.service.mapper;
import com.company.productservice.dto.ProductDto;
import com.company.productservice.module.Product;
import org.mapstruct.*;
@Mapper
public abstract class ProductMapper {
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Product toEntity(ProductDto dto) ;

    public abstract ProductDto toDto(Product product) ;
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Product updateUsersFromDto(ProductDto dto, @MappingTarget Product product);



}
