package com.test.service;

import com.test.dto.SensorDto;
import com.test.model.Sensor;

public interface SensorService {

    String createSensor(String name);
    SensorDto findByName(String name);

}
