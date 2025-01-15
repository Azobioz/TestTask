package com.test.controller;

import com.test.model.Sensor;
import com.test.repository.SensorRepository;
import com.test.service.SensorService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorControllerTest {

    @Mock
    private SensorService sensorService;
    @InjectMocks
    private SensorController sensorController;

    private Sensor sensor;

    @BeforeEach
    public void setUp() {
        sensor = new Sensor();
        sensor.setName("testName");
    }


    @Test
    public void registerNewSensor_Success() {
        when(sensorService.createSensor("testName")).thenReturn("Регистрация сенсора прошла успешна");

        String result = sensorController.registerNewSensor(sensor);

        Assertions.assertEquals("Регистрация сенсора прошла успешна", result);
    }

    @Test
    public void registerNewSensor_AlreadyExist() {
        when(sensorService.createSensor("testName")).thenReturn("Сенсор с таким названием уже существует");
        String result = sensorController.registerNewSensor(sensor);
        Assertions.assertEquals("Сенсор с таким названием уже существует", result);
    }


}
