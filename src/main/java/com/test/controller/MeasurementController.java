package com.test.controller;

import com.test.dto.MeasurementDto;
import com.test.model.Measurement;
import com.test.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public String addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.createMeasurement(measurement.getValue(), measurement.isRaining(), measurement.getSensor().getName());
    }

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public Map<String, Long> getRainyDaysCount() {
        return measurementService.getHowManyRainyDays();
    }


}
