package com.company.productservice.repository;


import com.company.productservice.module.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product>findByIdAndDeletedAtIsNull(Integer id);
    boolean existsByIdAndDeletedAtIsNull(Integer id);
    Optional<Product> findByProdNameAndDeletedAtIsNull(String name);
    List<Product> findByBasketIdAndDeletedAtIsNull(Integer id);
    @Query(value = "select * from product where deleted_at is null and amount>=: a", nativeQuery = true)
    List<Product>findAll(Double a);

}
