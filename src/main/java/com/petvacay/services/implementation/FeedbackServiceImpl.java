package com.petvacay.services.implementation;

import com.petvacay.constants.ErrorMessage;
import com.petvacay.dto.feedback.FeedbackDTO;
import com.petvacay.entities.Feedback;
import com.petvacay.exceptions.NoObjectFoundById;
import com.petvacay.mappers.feedback.FeedbackMapper;
import com.petvacay.repositories.FeedbackRepository;
import com.petvacay.services.FeedbackService;
import com.petvacay.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private FeedbackMapper feedbackMapper;
    private OrderService orderService;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, OrderService orderService) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.orderService = orderService;
    }

    @Override
    public FeedbackDTO findFeedbackForOrder(long orderId) {
        if (!orderService.existsOrderById(orderId)) {
            throw new NoObjectFoundById(ErrorMessage.ORDER_NOT_FOUND_BY_ID + orderId);
        }
        Feedback feedback = feedbackRepository.findFeedbackByOrderOrderId(orderId)
                .orElseThrow(() -> new NoObjectFoundById(ErrorMessage.FEEDBACK_NOT_FOUND_FOR_ORDER + orderId));
        return feedbackMapper.convertToDto(feedback);
    }

    @Override
    public FeedbackDTO createFeedback(Feedback feedback) {
        return null;
    }
}
