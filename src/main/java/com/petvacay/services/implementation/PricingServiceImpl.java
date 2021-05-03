package com.petvacay.services.implementation;

import com.petvacay.entities.Pricing;
import com.petvacay.services.PricingService;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {
    @Override
    public double calculatePricePerDay(Pricing pricing) {
        return 0;
    }
}
