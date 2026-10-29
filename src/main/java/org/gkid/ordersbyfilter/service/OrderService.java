package org.gkid.ordersbyfilter.service;

import org.gkid.ordersbyfilter.entity.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrderById(int orderId);
}
