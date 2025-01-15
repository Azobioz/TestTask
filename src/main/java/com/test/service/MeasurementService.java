package com.test.service;

import com.test.dto.MeasurementDto;

import java.util.List;
import java.util.Map;

public interface MeasurementService {

    String createMeasurement(double value, boolean isRaining, String sensorName);
    List<MeasurementDto> getAllMeasurements();
    Map<String, Long> getHowManyRainyDays();
}
