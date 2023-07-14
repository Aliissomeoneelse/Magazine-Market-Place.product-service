package com.company.productservice.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "productbase")
public class ProductBase {
    @Id
    @GeneratedValue(generator = "productbase_seq")
    @SequenceGenerator(name = "productbase_seq", sequenceName = "productbase_seq", allocationSize = 1)
    private Integer id;

    @OneToMany(mappedBy = "prodBaseId")
    Set<com.example.productcategory.module.Product> products;

    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "received_price")
    private Double receivedPrice;
    @Column(name = "selling_price")
    private Double sellingPrice;
    @Column(name = "prod_mass")
    private Double prodMass;
    private Double amount;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}