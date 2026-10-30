package org.gkid.ordersbyfilter;


import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.gkid.ordersbyfilter.entity.Order;
import org.gkid.ordersbyfilter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RateLimiter(name = "orderRateLimiter", fallbackMethod = "getOrdersRateLimiterFallback")
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable int orderId){

        Optional<Order> opt = orderService.getOrderById(orderId);

        if (opt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(opt.get(),HttpStatus.OK);
    }

    public ResponseEntity<Order> getOrdersRateLimiterFallback(@PathVariable int orderId, Throwable exception){

        Order order = new Order();

        // Return a response signaling the service failure gracefully (e.g. GTTP 503)
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(order);
    }

}
