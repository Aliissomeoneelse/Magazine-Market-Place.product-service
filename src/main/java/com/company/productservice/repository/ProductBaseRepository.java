package com.company.productservice.repository;

import com.company.productservice.module.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductBaseRepository extends JpaRepository<ProductBase,Integer> {
    Optional<ProductBase> findByIdAndDeletedAtIsNull(Integer id);
}