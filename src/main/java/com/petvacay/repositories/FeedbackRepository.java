package com.petvacay.repositories;

import com.petvacay.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findFeedbackByOrderOrderId(long orderId);
    boolean existsFeedbackByOrderOrderId(long orderId);
}
