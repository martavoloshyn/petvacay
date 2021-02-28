package com.petvacay.mappers.order;

import com.petvacay.dto.order.NewOrderDTO;
import com.petvacay.entities.Customer;
import com.petvacay.entities.Order;
import com.petvacay.entities.Performer;
import com.petvacay.mappers.GeneralMapper;
import com.petvacay.mappers.pet.PetGeneralInfoMapper;
import com.petvacay.mappers.user.UserNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewOrderMapper implements GeneralMapper<Order, NewOrderDTO> {

    private PetGeneralInfoMapper petGeneralInfoMapper;
    private UserNameMapper userNameMapper;

    @Autowired
    public NewOrderMapper(PetGeneralInfoMapper petGeneralInfoMapper, UserNameMapper userNameMapper) {
        this.petGeneralInfoMapper = petGeneralInfoMapper;
        this.userNameMapper = userNameMapper;
    }

    @Override
    public NewOrderDTO convertToDto(Order model) {
        return NewOrderDTO.builder()
                .comment(model.getComment())
                .customer(userNameMapper.convertToDto(model.getCustomer()))
                .endDate(model.getEndDate())
                .orderId(model.getOrderId())
                .orderStatus(model.getOrderStatus())
                .performer(userNameMapper.convertToDto(model.getPerformer()))
                .pets(petGeneralInfoMapper.convertListToDto(model.getPets()))
                .startDate(model.getStartDate())
                .build();
    }

    @Override
    public Order convertToModel(NewOrderDTO dto) {
        return Order.builder()
                .customer(new Customer(userNameMapper.convertToModel(dto.getCustomer())))
                .performer(new Performer(userNameMapper.convertToModel(dto.getPerformer())))
                .comment(dto.getComment())
                .endDate(dto.getEndDate())
                .orderId(dto.getOrderId())
                .orderStatus(dto.getOrderStatus())
                .pets(petGeneralInfoMapper.convertListToModel(dto.getPets()))
                .startDate(dto.getStartDate())
                .build();
    }
}
