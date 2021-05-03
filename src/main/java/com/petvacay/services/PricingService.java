package com.petvacay.services;

import com.petvacay.entities.Pricing;

public interface PricingService {
    double calculatePricePerDay(Pricing pricing);
}
