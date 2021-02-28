package com.petvacay.dto.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FeedbackDTO {

    private long feedbackId;

    private String text;

    private int stars;

    private Timestamp date;

    private long orderId;
}
