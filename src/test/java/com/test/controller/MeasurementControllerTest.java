package com.test.controller;

import com.test.dto.MeasurementDto;
import com.test.model.Measurement;
import com.test.model.Sensor;
import com.test.service.MeasurementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeasurementControllerTest {

    @Mock
    private MeasurementService measurementService;
    @InjectMocks
    private MeasurementController measurementController;

    @Test
    public void addMeasurement_Success() {
        Measurement measurement = new Measurement();
        measurement.setValue(20.1);
        measurement.setRaining(true);
        Sensor sensor = new Sensor();
        sensor.setName("test");
        measurement.setSensor(sensor);

        when(measurementService.createMeasurement(20.1, true, "test")).thenReturn("Измерения от сенсора добавлены");

        String result = measurementController.addMeasurement(measurement);

        Assertions.assertEquals("Измерения от сенсора добавлены", result);
    }

    @Test
    public void addMeasurement_SensorNotFound() {
        Measurement measurement = new Measurement();
        measurement.setValue(15.4);
        measurement.setRaining(false);
        Sensor sensor = new Sensor();
        sensor.setName("test1");
        measurement.setSensor(sensor);

        when(measurementService.createMeasurement(15.4, false, "test1")).thenReturn("Сенсора с таким названием нет");

        String result = measurementController.addMeasurement(measurement);

        Assertions.assertEquals("Сенсора с таким названием нет", result);
    }

    @Test
    public void addMeasurement_ValueFormError() {
        Measurement measurement = new Measurement();
        measurement.setValue(Double.NaN);
        measurement.setRaining(false);
        Sensor sensor = new Sensor();
        sensor.setName("test1");
        measurement.setSensor(sensor);

        when(measurementService.createMeasurement(Double.NaN, false, "test1")).thenReturn("Сенсора с таким названием нет");

        String result = measurementController.addMeasurement(measurement);

        Assertions.assertEquals("Сенсора с таким названием нет", result);
    }

    @Test
    public void getAllMeasurement_Success() {
        Sensor sensor = new Sensor();
        sensor.setName("test1");
        MeasurementDto measurement1 = new MeasurementDto(1, 14.1, true, sensor.getName());
        MeasurementDto measurement2 = new MeasurementDto(2, 20.5, false, sensor.getName());
        MeasurementDto measurement3 = new MeasurementDto(3, 19.9, false, sensor.getName());
        MeasurementDto measurement4 = new MeasurementDto(4, 5.1, true, sensor.getName());

        List<MeasurementDto> measurements = Arrays.asList(measurement1, measurement2, measurement3, measurement4);

        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        List<MeasurementDto> result = measurementController.getAllMeasurements();
        Assertions.assertNotNull(result);
    }

    @Test
    public void getAllMeasurement_Empty() {
        List<MeasurementDto> measurements = new ArrayList<>();

        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        List<MeasurementDto> result = measurementController.getAllMeasurements();
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void getRainyDaysCount_Success() {
        Sensor sensor = new Sensor();
        sensor.setName("test1");
        MeasurementDto measurement1 = new MeasurementDto(1, 14.1, true, sensor.getName());
        MeasurementDto measurement2 = new MeasurementDto(2, 20.5, false, sensor.getName());
        MeasurementDto measurement3 = new MeasurementDto(3, 19.9, false, sensor.getName());
        MeasurementDto measurement4 = new MeasurementDto(4, 5.1, true, sensor.getName());

        Map<String, Long> expectedResult = Map.of("rainyDaysCount", 4L);
        when(measurementService.getHowManyRainyDays()).thenReturn(expectedResult);

        Map<String, Long> result = measurementController.getRainyDaysCount();

        Assertions.assertEquals(result.get("rainyDaysCount"), expectedResult.get("rainyDaysCount"));

    }
}
