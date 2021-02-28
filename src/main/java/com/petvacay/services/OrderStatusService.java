package com.petvacay.services;

import com.petvacay.entities.OrderStatus;

public interface OrderStatusService {

    OrderStatus getOrderStatus (String orderStatusName);
}
