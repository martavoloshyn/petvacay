package com.petvacay.services;

import com.petvacay.dto.order.NewOrderDto;
import com.petvacay.dto.order.OrderDTO;
import com.petvacay.entities.OrderStatus;

public interface OrderService {

    OrderDTO getOrderById(long orderId);
    void updateOrderStatus(long orderId, OrderStatus orderStatus);
    NewOrderDto createOrder (NewOrderDto newOrder);
}
