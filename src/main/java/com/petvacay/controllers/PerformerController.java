package com.petvacay.controllers;

import com.petvacay.dto.performer.PerformerPreviewDTO;
import com.petvacay.services.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("petvacay/api/v1/performer")
public class PerformerController {

    private PerformerService performerService;

    @Autowired
    public PerformerController(PerformerService performerService) {
        this.performerService = performerService;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PerformerPreviewDTO>> filterPerformers(
            @RequestParam(value = "categories", required = false) List<Long> categories,
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "ddMMyyyy") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "ddMMyyyy") Date endDate,
            @RequestParam(value = "city", required = false) String city) {
        return new ResponseEntity<>(performerService.filterPerformers(categories,
                startDate,
                endDate,
                city), HttpStatus.OK);
    }
}
