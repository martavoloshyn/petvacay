package com.petvacay.mappers.feedback;

import com.petvacay.dto.feedback.FeedbackDTO;
import com.petvacay.entities.Feedback;
import com.petvacay.mappers.GeneralMapper;
import com.petvacay.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper implements GeneralMapper<Feedback, FeedbackDTO> {

    private OrderRepository orderRepository;

    @Autowired
    public FeedbackMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public FeedbackDTO convertToDto(Feedback model) {
        return FeedbackDTO.builder()
                .date(model.getDate())
                .feedbackId(model.getFeedbackId())
                .orderId(model.getOrder().getOrderId())
                .stars(model.getStars())
                .text(model.getText())
                .build();
    }

    @Override
    public Feedback convertToModel(FeedbackDTO dto) {
        return Feedback.builder()
                .date(dto.getDate())
                .feedbackId(dto.getFeedbackId())
                .order(orderRepository.getOne(dto.getOrderId()))
                .stars(dto.getStars())
                .text(dto.getText())
                .build();
    }
}
