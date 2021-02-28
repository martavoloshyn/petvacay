package com.petvacay.controllers;

import com.petvacay.dto.order.NewOrderDto;
import com.petvacay.dto.order.OrderDTO;
import com.petvacay.entities.Order;
import com.petvacay.entities.OrderStatus;
import com.petvacay.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("petvacay/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getProjectById(@PathVariable long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable long orderId, @RequestBody OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewOrderDto> createOrder(@RequestBody NewOrderDto newOrder) {
        return new ResponseEntity<>(orderService.createOrder(newOrder), HttpStatus.OK);
    }
}
