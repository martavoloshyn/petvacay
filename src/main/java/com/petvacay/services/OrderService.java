package com.petvacay.services;

import com.petvacay.dto.order.NewOrderDTO;
import com.petvacay.dto.order.OrderDTO;
import com.petvacay.entities.OrderStatus;

public interface OrderService {
    OrderDTO getOrderById(long orderId);
    void updateOrderStatus(long orderId, OrderStatus orderStatus);
    NewOrderDTO createOrder (NewOrderDTO newOrder);
}
