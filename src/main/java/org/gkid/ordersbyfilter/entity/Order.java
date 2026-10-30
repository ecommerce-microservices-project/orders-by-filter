package org.gkid.ordersbyfilter.entity;


import jakarta.persistence.*;
import org.gkid.ordersbyfilter.common.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class Order {

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Transient
    private List<OrderItem> items;

}