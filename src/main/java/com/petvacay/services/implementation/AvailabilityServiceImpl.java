package com.petvacay.services.implementation;

import com.petvacay.entities.Performer;
import com.petvacay.repositories.PerformerRepository;
import com.petvacay.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private PerformerRepository performerRepository;

    @Autowired
    public AvailabilityServiceImpl(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    @Override
    public List<Performer> getAvailablePerformers(Date startDate, Date endDate) {

        List<Performer> unavailablePerformers = performerRepository
                .findPerformerIdsByAvailableDates(startDate, endDate);

        return unavailablePerformers.isEmpty() ?
                performerRepository.findAll() : performerRepository.findByUserIdNotIn(unavailablePerformers);
    }

}
