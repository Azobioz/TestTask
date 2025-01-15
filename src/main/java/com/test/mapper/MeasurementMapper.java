package com.test.mapper;

import com.test.dto.MeasurementDto;
import com.test.model.Measurement;
import com.test.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;

public class MeasurementMapper {

    public static Measurement mapToMeasurement(MeasurementDto measurementDto) {
        return Measurement.builder()
                .measurementId(measurementDto.getMeasurementId())
                .value(measurementDto.getValue())
                .raining(measurementDto.isRaining())
                .build();
    }
    public static MeasurementDto mapToMeasurementDto(Measurement measurement) {
        return MeasurementDto.builder()
                .measurementId(measurement.getMeasurementId())
                .value(measurement.getValue())
                .raining(measurement.isRaining())
                .sensorName(measurement.getSensor().getName())
                .build();
    }

}
