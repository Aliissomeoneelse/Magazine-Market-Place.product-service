package com.company.productservice.repository;


import com.company.productservice.module.ProductBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductBaseRepositoryImpl {
    private final EntityManager entityManager;

    public Page<ProductBase> getProductBase(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt("size");
        }
        String strQuery = "select p from ProductBase p where 1=1 ";
        String countQuery = "select count(p.id) from ProductBase p where 1=1 ";
        StringBuilder buildParam = builderParams(params);
        Query query = entityManager.createQuery(strQuery + buildParam);
        Query queryTwo = entityManager.createQuery(countQuery + buildParam);

        setParams(query, params);
        setParams(queryTwo, params);

        query.setFirstResult(size * page);
        query.setMaxResults(size);
        long totalElement = Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElement);
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if(params.containsKey("id")){
            stringBuilder.append(" And p.id = :id");
        }
        if(params.containsKey("products")){
            stringBuilder.append(" And p.products = :products");
        }
        if(params.containsKey("prodName")){
            stringBuilder.append(" And p.prodName = :prodName");
        }
        if(params.containsKey("receivedPrice")){
            stringBuilder.append(" And p.receivedPrice = :receivedPrice");
        }
        if(params.containsKey("sellingPrice")){
            stringBuilder.append(" And p.sellingPrice = :sellingPrice");
        }
        if(params.containsKey("prodMass")){
            stringBuilder.append(" And p.prodMass = :prodMass");
        }
        if(params.containsKey("amount")){
            stringBuilder.append(" And p.amount = :amount");
        }
        return stringBuilder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter("id", params.get("id"));
        }
        if (params.containsKey("products")) {
            query.setParameter("products", params.get("products"));
        }
        if (params.containsKey("prodName")) {
            query.setParameter("prodName", params.get("prodName"));
        }
        if (params.containsKey("receivedPrice")) {
            query.setParameter("receivedPrice", params.get("receivedPrice"));
        }
        if (params.containsKey("sellingPrice")) {
            query.setParameter("sellingPrice", params.get("sellingPrice"));
        }
        if (params.containsKey("prodMass")) {
            query.setParameter("prodMass", params.get("prodMass"));
        }
        if (params.containsKey("amount")) {
            query.setParameter("amount", params.get("amount"));
        }
    }
}
