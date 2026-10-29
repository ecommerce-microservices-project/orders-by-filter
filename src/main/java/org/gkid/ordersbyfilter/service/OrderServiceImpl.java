package org.gkid.ordersbyfilter.service;

import org.gkid.ordersbyfilter.entity.Order;
import org.gkid.ordersbyfilter.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository repository;

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return repository.findById(orderId);
    }
}
