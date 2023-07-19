package com.company.productservice.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "received_price")
    private Double receivedPrice;
    @Column(name = "selling_price")
    private Double sellingPrice;
    @Column(name = "prod_mass")
    private Double prodMass;
    @Column(name="basket_id")
    private Integer basketId;
    //for foreignDebt
    @Column(name="foreign_id")
    private Integer foreignId;
    //for category
    //for product base
    @Column(name = "prod_base_id")
    private Integer prodBaseId;
    private Double amount;
    //one product to one image
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}