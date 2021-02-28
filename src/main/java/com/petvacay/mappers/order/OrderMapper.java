package com.petvacay.mappers.order;

import com.petvacay.dto.order.OrderDTO;
import com.petvacay.entities.Order;
import com.petvacay.mappers.GeneralMapper;
import com.petvacay.mappers.pet.PetGeneralInfoMapper;
import com.petvacay.mappers.user.UserNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements GeneralMapper<Order, OrderDTO> {

    private UserNameMapper userNameMapper;
    private PetGeneralInfoMapper petGeneralInfoMapper;

    @Autowired
    public OrderMapper(UserNameMapper userNameMapper, PetGeneralInfoMapper petGeneralInfoMapper) {
        this.userNameMapper = userNameMapper;
        this.petGeneralInfoMapper = petGeneralInfoMapper;
    }

    @Override
    public OrderDTO convertToDto(Order model) {
        return OrderDTO.builder()
                .comment(model.getComment())
                .customer(userNameMapper.convertToDto(model.getCustomer()))
                .endDate(model.getEndDate())
                .feedback(model.getFeedback())
                .orderId(model.getOrderId())
                .orderStatus(model.getOrderStatus().getOrderStatusName())
                .performer(userNameMapper.convertToDto(model.getPerformer()))
                .startDate(model.getStartDate())
                .pets(petGeneralInfoMapper.convertListToDto(model.getPets()))
                .build();
    }

    @Override
    public Order convertToModel(OrderDTO dto) {
        return null;
    }
}
