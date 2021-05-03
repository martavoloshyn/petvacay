package com.petvacay.services;

import com.petvacay.dto.performer.PerformerPreviewDTO;

import java.util.Date;
import java.util.List;

public interface PerformerService {
    List<PerformerPreviewDTO> filterPerformers(List<Long> categories,
                                               Date startDate,
                                               Date endDate,
                                               String city);
}
