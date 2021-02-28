package com.petvacay.services.implementation;

import com.petvacay.constants.ErrorMessage;
import com.petvacay.dto.feedback.FeedbackDTO;
import com.petvacay.entities.Feedback;
import com.petvacay.exceptions.NoObjectFoundById;
import com.petvacay.mappers.feedback.FeedbackMapper;
import com.petvacay.repositories.FeedbackRepository;
import com.petvacay.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public FeedbackDTO findFeedbackForOrder(long orderId) {
        try {
            return feedbackMapper.convertToDto(feedbackRepository.findFeedbackByOrderOrderId(orderId));
        } catch (NullPointerException e) {
            throw new NoObjectFoundById(ErrorMessage.ORDER_NOT_FOUND_BY_ID + orderId);
        }
    }

    @Override
    public FeedbackDTO createFeedback(Feedback feedback) {
        return null;
    }
}
