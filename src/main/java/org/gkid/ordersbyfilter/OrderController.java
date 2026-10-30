package org.gkid.ordersbyfilter;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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

    @Retry(name = "getOrdersRetry", fallbackMethod = "getOrdersFallback")
    @TimeLimiter(name = "orderTimeLimiter", fallbackMethod = "getOrdersFallback")
    @RateLimiter(name = "orderRateLimiter", fallbackMethod = "getOrdersFallback")
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable int orderId){

        Optional<Order> opt = orderService.getOrderById(orderId);

        if (opt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(opt.get(),HttpStatus.OK);
    }

    public ResponseEntity<Order> getOrdersFallback(@PathVariable int orderId, Throwable exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
    }
}