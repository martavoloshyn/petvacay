package com.petvacay.services;

import com.petvacay.entities.Performer;

import java.util.Date;
import java.util.List;

public interface AvailabilityService {
    List<Performer> getAvailablePerformers(Date startDate, Date endDate);
}
