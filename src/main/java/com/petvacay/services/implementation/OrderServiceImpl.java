package com.petvacay.services.implementation;

import com.petvacay.constants.ErrorMessage;
import com.petvacay.constants.OrderStatusConst;
import com.petvacay.dto.order.NewOrderDTO;
import com.petvacay.dto.order.OrderDTO;
import com.petvacay.entities.Order;
import com.petvacay.entities.OrderStatus;
import com.petvacay.exceptions.NoObjectFoundByIdException;
import com.petvacay.mappers.order.NewOrderMapper;
import com.petvacay.mappers.order.OrderMapper;
import com.petvacay.repositories.OrderRepository;
import com.petvacay.services.OrderService;
import com.petvacay.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private NewOrderMapper newOrderMapper;
    private OrderStatusService orderStatusService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            NewOrderMapper newOrderMapper,
                            OrderStatusService orderStatusService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.newOrderMapper = newOrderMapper;
        this.orderStatusService = orderStatusService;
    }

    @Override
    public OrderDTO getOrderById(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoObjectFoundByIdException(ErrorMessage.ORDER_NOT_FOUND_BY_ID + orderId));
        return orderMapper.convertToDto(order);
    }

    @Override
    public void updateOrderStatus(long orderId, OrderStatus orderStatus) {
        Order orderToUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoObjectFoundByIdException(ErrorMessage.ORDER_NOT_FOUND_BY_ID + orderId));
        orderToUpdate.setOrderStatus(orderStatus);
        orderRepository.save(orderToUpdate);
    }

    @Override
    public NewOrderDTO createOrder(NewOrderDTO newOrder) {
        return newOrderMapper.convertToDto(orderRepository.save(createOrderFromDtoData(newOrder)));
    }

    @Override
    public boolean existsOrderById(long orderId) {
        return orderRepository.existsOrderByOrderId(orderId);
    }

    private Order createOrderFromDtoData(NewOrderDTO newOrder) {
        Order order = newOrderMapper.convertToModel(newOrder);
        order.setOrderStatus(orderStatusService.getOrderStatus(OrderStatusConst.CREATED));
        return order;
    }
}
