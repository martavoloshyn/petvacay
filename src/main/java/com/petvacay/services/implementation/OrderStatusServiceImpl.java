package com.petvacay.services.implementation;

import com.petvacay.constants.ErrorMessage;
import com.petvacay.entities.OrderStatus;
import com.petvacay.exceptions.NoObjectFoundByIdException;
import com.petvacay.repositories.OrderStatusRepository;
import com.petvacay.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus getOrderStatus(String orderStatusName) {
        return orderStatusRepository.findOrderStatusByOrderStatusName(orderStatusName)
                .orElseThrow(() -> new NoObjectFoundByIdException(ErrorMessage.ORDER_STATUS_NOT_FOUND_BY_NAME + orderStatusName));
    }
}
