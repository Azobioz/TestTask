package com.test.service.serviceimpl;

import com.test.dto.SensorDto;
import com.test.model.Sensor;
import com.test.repository.SensorRepository;
import com.test.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.test.mapper.SensorMapper.mapToSensor;
import static com.test.mapper.SensorMapper.mapToSensorDto;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public String createSensor(String name) {
        if (sensorRepository.findByName(name) == null) {
            Sensor sensor = new Sensor();
            sensor.setName(name);
            sensorRepository.save(sensor);
            return "Регистрация сенсора прошла успешна";
        }
        return "Сенсор с таким названием уже существует";
    }

    @Override
    public SensorDto findByName(String name) {
        Sensor sensor = sensorRepository.findByName(name);
        if (sensor == null) {
            return null;
        }
        return mapToSensorDto(sensor);
    }
}
