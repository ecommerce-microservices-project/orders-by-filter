package org.gkid.ordersbyfilter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "products")
public class Product {

    @Id
    private int id;
    private String name;

    private String description;

    private int quantity;

    BigDecimal price;

    @Column(name = "category_id")
    private int categoryId;
}