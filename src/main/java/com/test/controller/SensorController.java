package com.test.controller;

import com.test.dto.SensorDto;
import com.test.model.Sensor;
import com.test.repository.SensorRepository;
import com.test.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public String registerNewSensor(@RequestBody Sensor sensor) {
       return sensorService.createSensor(sensor.getName());
    }

}
