package com.petvacay.repositories;

import com.petvacay.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findFeedbackByOrderOrderId(long orderId);
}
