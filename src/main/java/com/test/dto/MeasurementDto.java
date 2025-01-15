package com.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MeasurementDto {

    private final long measurementId;
    private final double value;
    private final boolean raining;
    private final String sensorName;

}
