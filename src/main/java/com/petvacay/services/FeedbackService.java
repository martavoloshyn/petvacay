package com.petvacay.services;

import com.petvacay.dto.feedback.FeedbackDTO;
import com.petvacay.entities.Feedback;

public interface FeedbackService {
    FeedbackDTO findFeedbackForOrder(long orderId);

    FeedbackDTO createFeedback(Feedback feedback);
}
