package com.test.mapper;

import com.test.dto.MeasurementDto;
import com.test.dto.SensorDto;
import com.test.model.Sensor;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.test.mapper.MeasurementMapper.mapToMeasurement;
import static com.test.mapper.MeasurementMapper.mapToMeasurementDto;

public class SensorMapper {

    public static Sensor mapToSensor(SensorDto sensorDto) {
        return Sensor.builder()
                .sensorId(sensorDto.getSensorId())
                .name(sensorDto.getName())
                .build();
    }

    public static SensorDto mapToSensorDto(Sensor sensor) {
        return SensorDto.builder()
                .sensorId(sensor.getSensorId())
                .name(sensor.getName())
                .measurements(sensor.getMeasurements())
                .build();
    }



}
